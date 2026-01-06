package com.hill91.witt.user.entity;

import jakarta.validation.constraints.Email;

public record UserUpdateDTO(
        String username,
        @Email String email,
        String password,
        String firstName,
        String lastName,
        String avatarUrl,
        Boolean enabled,
        String roles,
        Long workerId) {
}
