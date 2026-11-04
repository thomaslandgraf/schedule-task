package com.landgraf.scheduletask.infrastucture.repository;

import com.landgraf.scheduletask.infrastucture.entity.TasksEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksRepository extends MongoRepository<TasksEntity, String> {
}
