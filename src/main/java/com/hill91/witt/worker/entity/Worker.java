package com.hill91.witt.worker.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.smallrye.common.constraint.NotNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "worker")
public class Worker extends PanacheEntity {

    @Column(unique = true)
    @NotNull
    public String username;

    @Column(name = "first_name")
    @NotNull
    public String firstName;

    @Column(name = "last_name")
    @NotNull
    public String lastName;

    @Column(name = "avatar_url")
    public String avatarUrl;

    @Column(name = "created_at")
    @NotNull
    public LocalDateTime createdAt;

    @Column(name = "updated_at")
    public LocalDateTime updatedAt;

}
