package com.omb.boat;

import com.omb.boat.model.Boat;
import com.omb.boat.model.Category;
import com.omb.boat.port.inbound.BoatApiPort;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BoatApplicationService {

    private final BoatApiPort boatApiPort;

    public Uni<Boat> createNewBoat(String name, String category, String description, String registration) {
        Category categoryObj = Category.valueOf(category);
        return Uni.createFrom().item(() -> boatApiPort.createBoat(name, categoryObj, description, registration));
    }
}
