package com.landgraf.scheduletask.infrastucture.controller;

import com.landgraf.scheduletask.infrastucture.business.TasksService;
import com.landgraf.scheduletask.infrastucture.business.dto.TasksDTO;
import com.landgraf.scheduletask.infrastucture.enums.NotificationStatusEnum;
import com.landgraf.scheduletask.infrastucture.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TasksController {

    private final TasksService tasksService;

    @PostMapping
    public ResponseEntity<TasksDTO> registerTasks(@RequestBody TasksDTO tasksDTO,
                                                  @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tasksService.registerTasks(tasksDTO, token));
    }

    @GetMapping("/events")
    public ResponseEntity<List<TasksDTO>> getTasksBetweenDates(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finalDate) {
        return ResponseEntity.ok(tasksService.getTasksBetweenDates(startDate, finalDate));
    }

    @GetMapping("/email")
    public ResponseEntity<List<TasksDTO>> getTasksByUserEmail(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tasksService.getTasksByUserEmail(token));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteById(@RequestParam("id") String id){
        try {
            tasksService.deleteById(id);
        } catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Error trying delete task by id: " + id, e.getCause());
        }
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    public ResponseEntity<TasksDTO> changeNotificationStatus(@RequestParam("status") NotificationStatusEnum status,
                                                             @RequestParam("id") String id){
        return ResponseEntity.ok(tasksService.changeStatus(status, id));
    }

    @PutMapping
    public ResponseEntity<TasksDTO> updateTasks(@RequestBody TasksDTO tasksDTO,
                                                @RequestParam("id") String id){
        return ResponseEntity.ok(tasksService.updateTasks(tasksDTO, id));
    }
}
