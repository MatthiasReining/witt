package com.hill91.witt.worker.entity;

public record WorkerUpdateDTO(
        String username,
        String firstName,
        String lastName,
        String avatarUrl) {
}
