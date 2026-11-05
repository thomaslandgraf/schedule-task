package com.landgraf.scheduletask.infrastucture.business;

import com.landgraf.scheduletask.infrastucture.business.dto.TasksDTO;
import com.landgraf.scheduletask.infrastucture.business.mapper.TasksUpdateConverter;
import com.landgraf.scheduletask.infrastucture.business.mapper.TasksConverter;
import com.landgraf.scheduletask.infrastucture.entity.TasksEntity;
import com.landgraf.scheduletask.infrastucture.enums.NotificationStatusEnum;
import com.landgraf.scheduletask.infrastucture.exception.ResourceNotFoundException;
import com.landgraf.scheduletask.infrastucture.repository.TasksRepository;
import com.landgraf.scheduletask.infrastucture.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TasksService {

    private final TasksRepository tasksRepository;
    private final TasksConverter tasksConverter;
    private final TasksUpdateConverter tasksUpdateConverter;
    private final JwtUtil jwtUtil;

    public TasksDTO registerTasks(TasksDTO tasksDTO, String token){
        String email = jwtUtil.extractEmail(token.substring(7));
        tasksDTO.setCreateDate(LocalDateTime.now());
        tasksDTO.setNotificationStatusEnum(NotificationStatusEnum.PENDING);
        tasksDTO.setUserEmail(email);
        TasksEntity entity = tasksConverter.toTasksEntity(tasksDTO);

        return tasksConverter.toTasksDTO(tasksRepository.save(entity));
    }

    public List<TasksDTO> getTasksBetweenDates(LocalDateTime startDate, LocalDateTime finalDate){
        return tasksConverter.toTasksDtoList(tasksRepository.findByEventDateBetween(startDate, finalDate));
    }

    public List<TasksDTO> getTasksByUserEmail(String token){
        String email = jwtUtil.extractEmail(token.substring(7));
        return tasksConverter.toTasksDtoList(tasksRepository.findByUserEmail(email));
    }

    public void deleteById(String id){
        tasksRepository.deleteById(id);
    }

    public TasksDTO changeStatus(NotificationStatusEnum notificationStatusEnum, String id) {
        try{
            TasksEntity entity = tasksRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Task not found."));
            entity.setNotificationStatusEnum(notificationStatusEnum);
            return tasksConverter.toTasksDTO(tasksRepository.save(entity));
        } catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Error trying to change the status from the task ", e.getCause());
        }
    }

    public TasksDTO updateTasks(TasksDTO tasksDTO, String id){
        try{
        TasksEntity entity = tasksRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found."));
        tasksUpdateConverter.updateTasks(tasksDTO, entity);
        return tasksConverter.toTasksDTO(tasksRepository.save(entity));
        } catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Error trying to update the task ", e.getCause());
        }
    }
}
