package com.hill91.witt.timerecording.control;

import java.time.LocalDateTime;
import java.util.List;

import com.hill91.witt.timerecording.entity.CreateTimeRecordingDTO;
import com.hill91.witt.timerecording.entity.TimeRecording;
import com.hill91.witt.timerecording.entity.TimeRecordingDTO;
import com.hill91.witt.timerecording.entity.UpdateTimeRecordingDTO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class TimeRecordingService {

    @Inject
    TimeRecordingMapper mapper;

    public List<TimeRecordingDTO> getAllTimeRecordings() {
        List<TimeRecording> recordings = TimeRecording.listAll();
        return mapper.toDtoList(recordings);
    }

    public TimeRecordingDTO getTimeRecordingById(Long id) {
        TimeRecording recording = TimeRecording.findById(id);
        if (recording == null) {
            throw new NotFoundException("Time recording with id " + id + " not found");
        }
        return mapper.toDto(recording);
    }

    @Transactional
    public TimeRecordingDTO createTimeRecording(CreateTimeRecordingDTO dto) {
        TimeRecording recording = mapper.toEntity(dto);
        recording.createdAt = LocalDateTime.now();
        recording.persist();
        return mapper.toDto(recording);
    }

    @Transactional
    public TimeRecordingDTO updateTimeRecording(Long id, UpdateTimeRecordingDTO dto) {
        TimeRecording recording = TimeRecording.findById(id);
        if (recording == null) {
            throw new NotFoundException("Time recording with id " + id + " not found");
        }

        mapper.updateEntityFromDto(dto, recording);
        recording.updatedAt = LocalDateTime.now();
        recording.persist();

        return mapper.toDto(recording);
    }

    @Transactional
    public void deleteTimeRecording(Long id) {
        TimeRecording recording = TimeRecording.findById(id);
        if (recording == null) {
            throw new NotFoundException("Time recording with id " + id + " not found");
        }
        recording.delete();
    }
}
