package com.omb.boat.usecase;

import com.omb.boat.model.Boat;

import java.util.List;
import java.util.UUID;

public interface GetBoatUseCase {

    Boat getBoat(final UUID id);
    List<Boat> getBoats();
}
