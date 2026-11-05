package com.landgraf.scheduletask.infrastucture.entity;

import com.landgraf.scheduletask.infrastucture.enums.NotificationStatusEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("task")
public class TasksEntity {

    @Id
    private String id;
    private String taskName;
    private String description;
    private LocalDateTime createDate;
    private LocalDateTime eventDate;
    private String userEmail;
    private LocalDateTime changeDate;
    private NotificationStatusEnum notificationStatusEnum;
}
