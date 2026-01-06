package com.hill91.witt.user.entity;

import com.hill91.witt.worker.entity.Worker;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.smallrye.common.constraint.NotNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "app_user")
public class User extends PanacheEntity {

    @Column(unique = true)
    @NotNull
    public String username;

    @Column(unique = true)
    @NotNull
    public String email;

    @Column(name = "password_hash")
    @NotNull
    public String passwordHash;

    @Column(name = "first_name")
    @NotNull
    public String firstName;

    @Column(name = "last_name")
    @NotNull
    public String lastName;

    @Column(name = "avatar_url")
    public String avatarUrl;

    @Column(name = "last_login")
    public LocalDateTime lastLogin;

    @Column
    @NotNull
    public Boolean enabled;

    @Column
    public String roles;

    @OneToOne
    @JoinColumn(name = "worker_id")
    public Worker worker;

    @Column(name = "created_at")
    @NotNull
    public LocalDateTime createdAt;

    @Column(name = "updated_at")
    public LocalDateTime updatedAt;

}
