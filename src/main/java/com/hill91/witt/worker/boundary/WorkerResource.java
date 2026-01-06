package com.hill91.witt.worker.boundary;

import java.net.URI;
import java.util.List;

import com.hill91.witt.worker.control.WorkerService;
import com.hill91.witt.worker.entity.WorkerCreateDTO;
import com.hill91.witt.worker.entity.WorkerDTO;
import com.hill91.witt.worker.entity.WorkerUpdateDTO;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/workers")
public class WorkerResource {

    @Inject
    WorkerService workerService;

    @GET
    public List<WorkerDTO> getAllWorkers() {
        return workerService.getAllWorkers();
    }

    @GET
    @Path("/{id}")
    public WorkerDTO getWorker(@PathParam("id") Long id) {
        return workerService.getWorkerById(id);
    }

    @POST
    public Response createWorker(@Valid WorkerCreateDTO dto) {
        WorkerDTO created = workerService.createWorker(dto);
        return Response.created(URI.create("/workers/" + created.id()))
                .entity(created)
                .build();
    }

    @PUT
    @Path("/{id}")
    public WorkerDTO updateWorker(@PathParam("id") Long id, @Valid WorkerUpdateDTO dto) {
        return workerService.updateWorker(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteWorker(@PathParam("id") Long id) {
        workerService.deleteWorker(id);
        return Response.noContent().build();
    }
}
