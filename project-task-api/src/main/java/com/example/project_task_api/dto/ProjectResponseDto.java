package com.example.project_task_api.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectResponseDto {
    private Long id;
    private String name;
    private LocalDate deadline;

}