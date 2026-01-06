package com.hill91.witt.worker.control;

import java.time.LocalDateTime;
import java.util.List;

import com.hill91.witt.worker.entity.Worker;
import com.hill91.witt.worker.entity.WorkerCreateDTO;
import com.hill91.witt.worker.entity.WorkerDTO;
import com.hill91.witt.worker.entity.WorkerUpdateDTO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class WorkerService {

    @Inject
    WorkerMapper mapper;

    public List<WorkerDTO> getAllWorkers() {
        List<Worker> workers = Worker.listAll();
        return mapper.toDtoList(workers);
    }

    public WorkerDTO getWorkerById(Long id) {
        Worker worker = Worker.findById(id);
        if (worker == null) {
            throw new NotFoundException("Worker with id " + id + " not found");
        }
        return mapper.toDto(worker);
    }

    @Transactional
    public WorkerDTO createWorker(WorkerCreateDTO dto) {
        Worker worker = mapper.toEntity(dto);
        worker.createdAt = LocalDateTime.now();
        worker.persist();
        return mapper.toDto(worker);
    }

    @Transactional
    public WorkerDTO updateWorker(Long id, WorkerUpdateDTO dto) {
        Worker worker = Worker.findById(id);
        if (worker == null) {
            throw new NotFoundException("Worker with id " + id + " not found");
        }

        mapper.updateEntityFromDto(dto, worker);
        worker.updatedAt = LocalDateTime.now();
        worker.persist();
        return mapper.toDto(worker);
    }

    @Transactional
    public void deleteWorker(Long id) {
        Worker worker = Worker.findById(id);
        if (worker == null) {
            throw new NotFoundException("Worker with id " + id + " not found");
        }
        worker.delete();
    }
}
