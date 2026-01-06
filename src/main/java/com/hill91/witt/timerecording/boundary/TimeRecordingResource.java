package com.hill91.witt.timerecording.boundary;

import java.net.URI;
import java.util.List;

import com.hill91.witt.timerecording.control.TimeRecordingService;
import com.hill91.witt.timerecording.entity.TimeRecordingCreateDTO;
import com.hill91.witt.timerecording.entity.TimeRecordingDTO;
import com.hill91.witt.timerecording.entity.TimeRecordingUpdateDTO;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/time-recording")
public class TimeRecordingResource {

    @Inject
    TimeRecordingService timeRecordingService;

    @GET
    public List<TimeRecordingDTO> getAllTimeRecordings() {
        return timeRecordingService.getAllTimeRecordings();
    }

    @GET
    @Path("/{id}")
    public TimeRecordingDTO getTimeRecording(@PathParam("id") Long id) {
        return timeRecordingService.getTimeRecordingById(id);
    }

    @POST
    public Response createTimeRecording(@Valid TimeRecordingCreateDTO dto) {
        TimeRecordingDTO created = timeRecordingService.createTimeRecording(dto);
        return Response.created(URI.create("/time-recording/" + created.id()))
                .entity(created)
                .build();
    }

    @PUT
    @Path("/{id}")
    public TimeRecordingDTO updateTimeRecording(@PathParam("id") Long id, @Valid TimeRecordingUpdateDTO dto) {
        return timeRecordingService.updateTimeRecording(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTimeRecording(@PathParam("id") Long id) {
        timeRecordingService.deleteTimeRecording(id);
        return Response.noContent().build();
    }
}
