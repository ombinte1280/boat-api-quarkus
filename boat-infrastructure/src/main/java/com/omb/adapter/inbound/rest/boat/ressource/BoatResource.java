package com.omb.adapter.inbound.rest.boat.ressource;


import com.omb.adapter.inbound.rest.boat.dto.CreateBoatRequest;
import com.omb.adapter.inbound.rest.boat.dto.PatchBoatRequest;
import com.omb.boat.BoatApplicationService;
import com.omb.boat.model.Boat;
import io.smallrye.mutiny.Uni;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Path("/boats")
public class BoatResource {

    private final BoatApplicationService boatApplication;

    public BoatResource(BoatApplicationService boatApplication) {
        this.boatApplication = boatApplication;
    }

    @POST
    public Response createBoat(@Valid CreateBoatRequest request) {

        Boat savedBoat = boatApplication.createNewBoat(
                request.name(),
                request.category(),
                request.description(),
                request.registration()
        );
        return Response.status(Response.Status.CREATED)
                .entity(savedBoat)
                .build();
    }

    @GET
    public Response getBoats() {
        List<Boat> boats = boatApplication.getAllBoats();
        return Response.status(Response.Status.OK)
                .entity(boats)
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getBoatById(@PathParam("id") UUID id) {
        Boat boat = boatApplication.getBoatById(id);
        return Response.status(Response.Status.OK)
                .entity(boat)
                .build();
    }

    @PATCH
    @Path("/{id}")
    public Response updateBoat(@PathParam("id") UUID id, @Valid PatchBoatRequest request) {
        Boat boat = boatApplication.updateBoat(id, request.name(), request.category(), request.description(), request.registration());
        return Response.status(Response.Status.OK)
                .entity(boat)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBoat(@PathParam("id") UUID id) {
        boatApplication.deleteBoat(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
