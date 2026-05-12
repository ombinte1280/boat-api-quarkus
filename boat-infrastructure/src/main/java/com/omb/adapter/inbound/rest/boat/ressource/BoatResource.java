package com.omb.adapter.inbound.rest.boat.ressource;


import com.omb.adapter.inbound.rest.boat.dto.CreateBoatRequest;
import com.omb.boat.BoatApplicationService;
import io.smallrye.mutiny.Uni;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@Path("/boats")
@RequiredArgsConstructor
public class BoatResource {

    private final BoatApplicationService boatApplication;

    @POST
    public Uni<Response> createBoat(@Valid CreateBoatRequest request) {
        return boatApplication.createNewBoat(request.name(), request.category(), request.description(), request.registration())
                .onItem()
                .transform(savedBoat -> Response.status(Response.Status.CREATED)
                        .entity(savedBoat)
                        .build());
    }
}
