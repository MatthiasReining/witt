package com.hill91.witt.user.control;

import java.time.LocalDateTime;
import java.util.List;

import com.hill91.witt.user.entity.User;
import com.hill91.witt.user.entity.UserCreateDTO;
import com.hill91.witt.user.entity.UserDTO;
import com.hill91.witt.user.entity.UserUpdateDTO;
import com.hill91.witt.worker.entity.Worker;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UserService {

    @Inject
    UserMapper mapper;

    public List<UserDTO> getAllUsers() {
        List<User> users = User.listAll();
        return mapper.toDtoList(users);
    }

    public UserDTO getUserById(Long id) {
        User user = User.findById(id);
        if (user == null) {
            throw new NotFoundException("User with id " + id + " not found");
        }
        return mapper.toDto(user);
    }

    @Transactional
    public UserDTO createUser(UserCreateDTO dto) {
        // Check if workerId is provided and valid
        if (dto.workerId() != null) {
            Worker worker = Worker.findById(dto.workerId());
            if (worker == null) {
                throw new NotFoundException("Worker with id " + dto.workerId() + " not found");
            }
        }

        User user = mapper.toEntity(dto);
        // user.passwordHash = BcryptUtil.bcryptHash(dto.password());
        // FIXME: For testing purposes only, do not use in production
        user.passwordHash = dto.password();
        user.enabled = true;
        user.createdAt = LocalDateTime.now();

        // Link worker if provided
        if (dto.workerId() != null) {
            user.worker = Worker.findById(dto.workerId());
        }

        user.persist();
        return mapper.toDto(user);
    }

    @Transactional
    public UserDTO updateUser(Long id, UserUpdateDTO dto) {
        User user = User.findById(id);
        if (user == null) {
            throw new NotFoundException("User with id " + id + " not found");
        }

        // Check if workerId is provided and valid
        if (dto.workerId() != null) {
            Worker worker = Worker.findById(dto.workerId());
            if (worker == null) {
                throw new NotFoundException("Worker with id " + dto.workerId() + " not found");
            }
            user.worker = worker;
        }

        // Update password if provided
        if (dto.password() != null && !dto.password().isBlank()) {
            // user.passwordHash = BcryptUtil.bcryptHash(dto.password());
            user.passwordHash = dto.password();
        }

        mapper.updateEntityFromDto(dto, user);
        user.updatedAt = LocalDateTime.now();
        user.persist();

        return mapper.toDto(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = User.findById(id);
        if (user == null) {
            throw new NotFoundException("User with id " + id + " not found");
        }
        user.delete();
    }

    @Transactional
    public void updateLastLogin(Long id) {
        User user = User.findById(id);
        if (user != null) {
            user.lastLogin = LocalDateTime.now();
            user.persist();
        }
    }
}
