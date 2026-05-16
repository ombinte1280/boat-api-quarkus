package com.omb.boat.port.outbound;

import com.omb.boat.model.Boat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BoatPersistencePort {

    Boat save(Boat boat);
    Optional<Boat> findById(UUID id);
    List<Boat> findAll();
    void deleteById(UUID id);
}
