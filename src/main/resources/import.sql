-- Insert Users
INSERT INTO app_user (id, username, email, password_hash, first_name, last_name, avatar_url, last_login, enabled, roles) VALUES
(1, 'admin', 'admin@witt.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'John', 'Doe', 'https://i.pravatar.cc/150?img=12', '2026-01-05 14:30:00', true, 'ADMIN,USER'),
(2, 'manager', 'manager@witt.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Sarah', 'Smith', 'https://i.pravatar.cc/150?img=5', '2026-01-06 08:15:00', true, 'MANAGER,USER');

-- Insert Workers
INSERT INTO worker (id, username, first_name, last_name, avatar_url, created_at, updated_at) VALUES
(1, 'worker.mike', 'Mike', 'Johnson', 'https://i.pravatar.cc/150?img=33', '2025-12-01 09:00:00', '2025-12-01 09:00:00'),
(2, 'worker.anna', 'Anna', 'Williams', 'https://i.pravatar.cc/150?img=20', '2025-12-01 09:00:00', '2025-12-01 09:00:00'),
(3, 'worker.david', 'David', 'Brown', 'https://i.pravatar.cc/150?img=15', '2025-12-01 09:00:00', '2025-12-01 09:00:00');

-- Insert Time Recordings
INSERT INTO time_recording (id, worker_id, description, start_time, end_time, project_name, created_at, updated_at) VALUES
(1, 1, 'Backend API development for authentication module', '2026-01-02 08:00:00', '2026-01-02 12:00:00', 'Project Alpha', '2026-01-02 08:00:00', '2026-01-02 12:00:00'),
(2, 1, 'Code review and bug fixing', '2026-01-02 13:00:00', '2026-01-02 17:00:00', 'Project Alpha', '2026-01-02 13:00:00', '2026-01-02 17:00:00'),
(3, 2, 'Database schema design and optimization', '2026-01-03 08:30:00', '2026-01-03 11:30:00', 'Project Beta', '2026-01-03 08:30:00', '2026-01-03 11:30:00'),
(4, 2, 'Frontend integration with REST API', '2026-01-03 12:30:00', '2026-01-03 16:00:00', 'Project Beta', '2026-01-03 12:30:00', '2026-01-03 16:00:00'),
(5, 3, 'Unit test implementation for service layer', '2026-01-03 09:00:00', '2026-01-03 12:00:00', 'Project Gamma', '2026-01-03 09:00:00', '2026-01-03 12:00:00'),
(6, 3, 'Docker containerization setup', '2026-01-03 13:00:00', '2026-01-03 15:30:00', 'Project Gamma', '2026-01-03 13:00:00', '2026-01-03 15:30:00'),
(7, 1, 'Sprint planning meeting and task estimation', '2026-01-04 09:00:00', '2026-01-04 10:30:00', 'Project Alpha', '2026-01-04 09:00:00', '2026-01-04 10:30:00'),
(8, 2, 'Performance testing and profiling', '2026-01-04 10:00:00', '2026-01-04 14:00:00', 'Project Beta', '2026-01-04 10:00:00', '2026-01-04 14:00:00'),
(9, 3, 'Documentation updates and README improvement', '2026-01-05 08:00:00', '2026-01-05 11:00:00', 'Project Gamma', '2026-01-05 08:00:00', '2026-01-05 11:00:00'),
(10, 1, 'CI/CD pipeline configuration', '2026-01-05 14:00:00', '2026-01-05 17:00:00', 'Project Alpha', '2026-01-05 14:00:00', '2026-01-05 17:00:00');

-- Reset sequences for PostgreSQL (if using PostgreSQL)
-- SELECT setval('app_user_seq', (SELECT MAX(id) FROM app_user));
-- SELECT setval('worker_seq', (SELECT MAX(id) FROM worker));
-- SELECT setval('time_recording_seq', (SELECT MAX(id) FROM time_recording));

-- For H2 database (common in dev mode), sequences are handled automatically
