package com.hill91.witt.timerecording.control;

import com.hill91.witt.timerecording.entity.TimeRecordingCreateDTO;
import com.hill91.witt.timerecording.entity.TimeRecording;
import com.hill91.witt.timerecording.entity.TimeRecordingDTO;
import com.hill91.witt.timerecording.entity.TimeRecordingUpdateDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TimeRecordingMapper {

    TimeRecordingDTO toDto(TimeRecording entity);

    List<TimeRecordingDTO> toDtoList(List<TimeRecording> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    TimeRecording toEntity(TimeRecordingCreateDTO dto);

    void updateEntityFromDto(TimeRecordingUpdateDTO dto, @MappingTarget TimeRecording entity);
}
