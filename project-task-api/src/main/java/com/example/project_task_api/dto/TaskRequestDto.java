package com.example.project_task_api.dto;

import lombok.Data;

@Data
public class TaskRequestDto {
    private String description;
    private String status;

}