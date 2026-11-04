package com.landgraf.scheduletask.infrastucture.business;

import com.landgraf.scheduletask.infrastucture.business.dto.TasksDTO;
import com.landgraf.scheduletask.infrastucture.business.mapper.TasksConverter;
import com.landgraf.scheduletask.infrastucture.entity.TasksEntity;
import com.landgraf.scheduletask.infrastucture.enums.NotificationStatusEnum;
import com.landgraf.scheduletask.infrastucture.repository.TasksRepository;
import com.landgraf.scheduletask.infrastucture.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TasksService {

    private final TasksRepository tasksRepository;
    private final TasksConverter tasksConverter;
    private final JwtUtil jwtUtil;

    public TasksDTO registerTasks(TasksDTO tasksDTO, String token){
        String email = jwtUtil.extractEmail(token.substring(7));
        tasksDTO.setCreateDate(LocalDateTime.now());
        tasksDTO.setNotificationStatusEnum(NotificationStatusEnum.PENDING);
        tasksDTO.setUserEmail(email);
        TasksEntity entity = tasksConverter.toTasksEntity(tasksDTO);

        return tasksConverter.toTasksDTO(tasksRepository.save(entity));
    }
}
