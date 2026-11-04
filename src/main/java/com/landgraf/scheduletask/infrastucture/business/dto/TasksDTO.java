package com.landgraf.scheduletask.infrastucture.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.landgraf.scheduletask.infrastucture.enums.NotificationStatusEnum;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TasksDTO {

    private String id;
    private String taskName;
    private String description;
    private LocalDateTime createDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss")
    private LocalDateTime eventDate;
    private String userEmail;
    private LocalDateTime changeDate;
    private NotificationStatusEnum notificationStatusEnum;
}
