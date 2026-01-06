package com.hill91.witt.timerecording.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TimeRecordingCreateDTO(
                @NotNull(message = "Worker ID is required") Long workerId,
                @NotBlank(message = "Description is required") String description,
                @NotNull(message = "Start time is required") @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime startTime,
                @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime endTime,
                String projectName) {
}
