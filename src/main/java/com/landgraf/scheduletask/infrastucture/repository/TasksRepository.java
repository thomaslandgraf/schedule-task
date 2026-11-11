package com.landgraf.scheduletask.infrastucture.repository;

import com.landgraf.scheduletask.infrastucture.entity.TasksEntity;
import com.landgraf.scheduletask.infrastucture.enums.NotificationStatusEnum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TasksRepository extends MongoRepository<TasksEntity, String> {

    List<TasksEntity> findByEventDateBetweenAndNotificationStatusEnum(LocalDateTime startDate,
                                                                      LocalDateTime finalDate,
                                                                      NotificationStatusEnum notificationStatusEnum);
    List<TasksEntity> findByUserEmail(String email);
}
