package com.example.project_task_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TaskResponseDto {
    private Long id;

    @JsonProperty("project_id")
    private Long projectId;

    @JsonProperty("task_name")
    private String description;
    private String status;

}