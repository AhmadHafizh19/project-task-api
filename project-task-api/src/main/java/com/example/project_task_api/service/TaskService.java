package com.example.project_task_api.service;

import com.example.project_task_api.dto.TaskRequestDto;
import com.example.project_task_api.dto.TaskResponseDto;
import com.example.project_task_api.dto.TaskStatusUpdateRequestDto;
import com.example.project_task_api.model.Project;
import com.example.project_task_api.model.Task;
import com.example.project_task_api.model.TaskStatus;
import com.example.project_task_api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectService projectService;

    public TaskResponseDto addTaskToProject(Long projectId, TaskRequestDto request) {
        Project project = projectService.getProjectById(projectId);

        Task task = Task.builder()
                .projectId(project.getId())
                .description(request.getDescription())
                .status(TaskStatus.valueOf(request.getStatus()))
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
        List<Task> tasks = taskRepository.findByProjectId(projectId);
        return tasks.stream().map(task -> TaskResponseDto.builder()
                .id(task.getId())
                .projectId(task.getProjectId())
                .description(task.getDescription())
                .status(task.getStatus().name())
                .build()
        ).collect(Collectors.toList());
    }

    public TaskResponseDto updateTaskStatus(Long taskId, TaskStatusUpdateRequestDto request) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
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