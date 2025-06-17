package com.example.project_task_api.service;

import com.example.project_task_api.dto.TaskRequestDto;
import com.example.project_task_api.dto.TaskResponseDto;
import com.example.project_task_api.dto.TaskStatusUpdateRequestDto;
import com.example.project_task_api.model.Project;
import com.example.project_task_api.model.Task;
import com.example.project_task_api.model.TaskStatus;
import com.example.project_task_api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectService projectService;

    public TaskResponseDto addTaskToProject(Long projectId, TaskRequestDto request) {
        Project project;
        try {
            project = projectService.getProjectById(projectId);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found");
        }

        if (request.getDescription() == null || request.getStatus() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Description and status must not be null");
        }

        TaskStatus status;
        try {
            status = TaskStatus.valueOf(request.getStatus());
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid status: " + request.getStatus());
        }

        Task task = Task.builder()
                .projectId(project.getId())
                .description(request.getDescription())
                .status(status)
                .build();

        Task saved = taskRepository.save(task);

        return TaskResponseDto.builder()
                .id(saved.getId())
                .projectId(saved.getProjectId())
                .description(saved.getDescription())
                .status(saved.getStatus().name())
                .build();
    }

    public List<TaskResponseDto> getTasksByProjectId(Long projectId) {
        // Ensure project exists
        projectService.getProjectById(projectId);

        List<Task> tasks = taskRepository.findByProjectId(projectId);
        return tasks.stream().map(task -> TaskResponseDto.builder()
                .id(task.getId())
                .projectId(task.getProjectId())
                .description(task.getDescription())
                .status(task.getStatus().name())
                .build()
        ).collect(Collectors.toList());
    }

    public TaskResponseDto updateTaskStatus(Long projectId, Long taskId, TaskStatusUpdateRequestDto request) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
        if (!task.getProjectId().equals(projectId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task does not belong to this project");
        }
        task.setStatus(TaskStatus.valueOf(request.getStatus()));
        Task updated = taskRepository.save(task);

        return TaskResponseDto.builder()
                .id(updated.getId())
                .projectId(updated.getProjectId())
                .description(updated.getDescription())
                .status(updated.getStatus().name())
                .build();
    }
}