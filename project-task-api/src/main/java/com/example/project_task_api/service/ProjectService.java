package com.example.project_task_api.service;

import com.example.project_task_api.dto.ProjectRequestDto;
import com.example.project_task_api.dto.ProjectResponseDto;
import com.example.project_task_api.model.Project;
import com.example.project_task_api.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectResponseDto createProject(ProjectRequestDto request) {
        if (request.getDeadline() != null && request.getDeadline().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Deadline cannot be in the past");
        }
        Project project = Project.builder()
                .name(request.getName())
                .deadline(request.getDeadline())
                .build();
        Project saved = projectRepository.save(project);

        return ProjectResponseDto.builder()
                .id(saved.getId())
                .name(saved.getName())
                .deadline(saved.getDeadline())
                .build();
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));
    }
}