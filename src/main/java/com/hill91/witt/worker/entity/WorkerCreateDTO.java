package com.hill91.witt.worker.entity;

import jakarta.validation.constraints.NotBlank;

public record WorkerCreateDTO(
        @NotBlank String username,
        @NotBlank String firstName,
        @NotBlank String lastName,
        String avatarUrl) {
}
