package com.hill91.witt.user.control;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.hill91.witt.user.entity.User;
import com.hill91.witt.user.entity.UserCreateDTO;
import com.hill91.witt.user.entity.UserDTO;
import com.hill91.witt.user.entity.UserUpdateDTO;

@Mapper(componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mapping(target = "workerId", source = "worker.id")
    @Mapping(target = "workerUsername", source = "worker.username")
    UserDTO toDto(User entity);

    List<UserDTO> toDtoList(List<User> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "worker", ignore = true)
    @Mapping(target = "lastLogin", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    User toEntity(UserCreateDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastLogin", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "worker", ignore = true)
    void updateEntityFromDto(UserUpdateDTO dto, @MappingTarget User entity);
}
