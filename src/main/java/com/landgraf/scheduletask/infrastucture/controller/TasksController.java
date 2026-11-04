package com.landgraf.scheduletask.infrastucture.controller;

import com.landgraf.scheduletask.infrastucture.business.TasksService;
import com.landgraf.scheduletask.infrastucture.business.dto.TasksDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
