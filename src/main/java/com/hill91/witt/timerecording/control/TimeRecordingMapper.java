package com.hill91.witt.timerecording.control;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.hill91.witt.timerecording.entity.TimeRecording;
import com.hill91.witt.timerecording.entity.TimeRecordingCreateDTO;
import com.hill91.witt.timerecording.entity.TimeRecordingDTO;
import com.hill91.witt.timerecording.entity.TimeRecordingUpdateDTO;

@Mapper(componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TimeRecordingMapper {

    @Mapping(target = "workerId", expression = "java(entity.worker != null ? entity.worker.id : null)")
    @Mapping(target = "workerUsername", expression = "java(entity.worker != null ? entity.worker.username : null)")
    TimeRecordingDTO toDto(TimeRecording entity);

    List<TimeRecordingDTO> toDtoList(List<TimeRecording> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "worker", ignore = true)
    TimeRecording toEntity(TimeRecordingCreateDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "worker", ignore = true)
    void updateEntityFromDto(TimeRecordingUpdateDTO dto, @MappingTarget TimeRecording entity);
}
