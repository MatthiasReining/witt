package com.hill91.witt.timerecording.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.smallrye.common.constraint.NotNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "time_recording")
public class TimeRecording extends PanacheEntity {

    @Column
    @NotNull
    public String description;

    @Column(name = "start_time")
    @NotNull
    public LocalDateTime startTime;

    @Column(name = "end_time")
    public LocalDateTime endTime;

    @Column(name = "project_name")
    public String projectName;

    @Column(name = "created_at")
    @NotNull
    public LocalDateTime createdAt;

    @Column(name = "updated_at")
    public LocalDateTime updatedAt;

}
