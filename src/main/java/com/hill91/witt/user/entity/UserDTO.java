package com.hill91.witt.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record UserDTO(
        Long id,
        String username,
        String email,
        String firstName,
        String lastName,
        String avatarUrl,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime lastLogin,
        Boolean enabled,
        String roles,
        Long workerId,
        String workerUsername,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime createdAt,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime updatedAt) {
}
