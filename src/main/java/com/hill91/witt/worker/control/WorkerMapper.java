package com.hill91.witt.worker.control;

import com.hill91.witt.worker.entity.Worker;
import com.hill91.witt.worker.entity.WorkerCreateDTO;
import com.hill91.witt.worker.entity.WorkerDTO;
import com.hill91.witt.worker.entity.WorkerUpdateDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface WorkerMapper {

    WorkerDTO toDto(Worker entity);

    List<WorkerDTO> toDtoList(List<Worker> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Worker toEntity(WorkerCreateDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(WorkerUpdateDTO dto, @MappingTarget Worker entity);
}
