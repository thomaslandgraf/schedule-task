package com.landgraf.scheduletask.infrastucture.controller;

import com.landgraf.scheduletask.infrastucture.business.TasksService;
import com.landgraf.scheduletask.infrastucture.business.dto.TasksDTO;
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
}
