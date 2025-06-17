package com.example.project_task_api.controller;

import com.example.project_task_api.dto.ProjectRequestDto;
import com.example.project_task_api.dto.ProjectResponseDto;
import com.example.project_task_api.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectResponseDto> createProject(@RequestBody ProjectRequestDto request) {
        ProjectResponseDto response = projectService.createProject(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> getProjectById(@PathVariable Long id) {
        var project = projectService.getProjectById(id);
        ProjectResponseDto response = ProjectResponseDto.builder()
                .id(project.getId())
                .name(project.getName())
                .deadline(project.getDeadline())
                .build();
        return ResponseEntity.ok(response);
    }
}