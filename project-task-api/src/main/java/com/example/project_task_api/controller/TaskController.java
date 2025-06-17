package com.example.project_task_api.controller;

import com.example.project_task_api.dto.TaskRequestDto;
import com.example.project_task_api.dto.TaskResponseDto;
import com.example.project_task_api.dto.TaskStatusUpdateRequestDto;
import com.example.project_task_api.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects/{projectId}/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponseDto> addTask(
            @PathVariable Long projectId,
            @RequestBody TaskRequestDto request) {
        TaskResponseDto response = taskService.addTaskToProject(projectId, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> getTasks(@PathVariable Long projectId) {
        List<TaskResponseDto> tasks = taskService.getTasksByProjectId(projectId);
        return ResponseEntity.ok(tasks);
    }

    @PatchMapping("/{taskId}/status")
    public ResponseEntity<TaskResponseDto> updateTaskStatus(
            @PathVariable Long projectId,
            @PathVariable Long taskId,
            @RequestBody TaskStatusUpdateRequestDto request) {
        TaskResponseDto response = taskService.updateTaskStatus(projectId, taskId, request);
        return ResponseEntity.ok(response);
    }
}