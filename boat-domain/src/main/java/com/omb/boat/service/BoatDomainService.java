package com.omb.boat.service;

import com.omb.boat.model.Boat;
import com.omb.boat.model.Category;
import com.omb.boat.port.inbound.BoatApiPort;

import java.util.List;
import java.util.UUID;

public class BoatDomainService implements BoatApiPort {

    @Override
    public Boat createBoat(String name, Category category, String description, String registration) {
        return null;
    }

    @Override
    public void deleteBoat(UUID id) {

    }

    @Override
    public Boat getBoat(UUID id) {
        return null;
    }

    @Override
    public List<Boat> getBoats() {
        return List.of();
    }

    @Override
    public Boat updateBoat(Boat boat) {
        return null;
    }
}
