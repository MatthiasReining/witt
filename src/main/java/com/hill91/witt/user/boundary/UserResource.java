package com.hill91.witt.user.boundary;

import java.net.URI;
import java.util.List;

import com.hill91.witt.user.control.UserService;
import com.hill91.witt.user.entity.UserCreateDTO;
import com.hill91.witt.user.entity.UserDTO;
import com.hill91.witt.user.entity.UserUpdateDTO;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/users")
public class UserResource {

    @Inject
    UserService userService;

    @GET
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GET
    @Path("/{id}")
    public UserDTO getUser(@PathParam("id") Long id) {
        return userService.getUserById(id);
    }

    @POST
    public Response createUser(@Valid UserCreateDTO dto) {
        UserDTO created = userService.createUser(dto);
        return Response.created(URI.create("/users/" + created.id()))
                .entity(created)
                .build();
    }

    @PUT
    @Path("/{id}")
    public UserDTO updateUser(@PathParam("id") Long id, @Valid UserUpdateDTO dto) {
        return userService.updateUser(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        userService.deleteUser(id);
        return Response.noContent().build();
    }
}
