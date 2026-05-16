package com.omb.boat;

import com.omb.boat.model.Boat;
import com.omb.boat.model.Category;
import com.omb.boat.port.inbound.BoatApiPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BoatApplicationService {

    private final BoatApiPort boatApiPort;

    public Boat createNewBoat(String name, String category, String description, String registration) {
        log.info("Create new boat");
        Boat newBoat = new Boat(UUID.randomUUID(), name, Category.valueOf(category), description, registration, LocalDate.now());
        return boatApiPort.saveBoat(newBoat);
    }

    public List<Boat> getAllBoats() {
        return boatApiPort.getBoats();
    }

    public Boat getBoatById(UUID id) {
        return boatApiPort.getBoat(id);
    }

    public Boat updateBoat(UUID id, String name, Category category, String description, String registration) {

        Boat toBeUpdated = boatApiPort.getBoat(id);

        if(name != null) {
            toBeUpdated.setName(name);
        }
        if (category != null) {
            toBeUpdated.setCategory(category);
        }
        if(description != null) {
            toBeUpdated.setDescription(description);
        }
        if(registration != null) {
            toBeUpdated.setRegistration(registration);
        }

        return boatApiPort.saveBoat(toBeUpdated);
    }

    public void deleteBoat(UUID id) {
        boatApiPort.deleteBoat(id);
    }
}
