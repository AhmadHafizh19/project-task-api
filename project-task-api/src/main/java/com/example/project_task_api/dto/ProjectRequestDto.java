package com.example.project_task_api.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectRequestDto {
    private String name;
    private LocalDate deadline;
}