package com.landgraf.scheduletask.infrastucture.business.mapper;

import com.landgraf.scheduletask.infrastucture.business.dto.TasksDTO;
import com.landgraf.scheduletask.infrastucture.entity.TasksEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TasksUpdateConverter {

    void updateTasks(TasksDTO tasksDTO, @MappingTarget TasksEntity tasksEntity);
}
