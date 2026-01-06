package com.hill91.witt.timerecording.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record TimeRecordingUpdateDTO(
                Long workerId,
                String description,
                @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime startTime,
                @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime endTime,
                String projectName) {
}
