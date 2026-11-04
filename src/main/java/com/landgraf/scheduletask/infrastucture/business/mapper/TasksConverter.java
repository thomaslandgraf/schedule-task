package com.landgraf.scheduletask.infrastucture.business.mapper;

import com.landgraf.scheduletask.infrastucture.business.dto.TasksDTO;
import com.landgraf.scheduletask.infrastucture.entity.TasksEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TasksConverter {

    TasksEntity toTasksEntity(TasksDTO tasksDTO);
    TasksDTO toTasksDTO(TasksEntity tasksEntity);
    List<TasksDTO> toTasksDtoList(List<TasksEntity> tasksEntity);
    List<TasksEntity> toTasksEntityList(List<TasksDTO> tasksDTO);
}
