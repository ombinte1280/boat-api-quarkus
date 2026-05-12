package com.omb.boat.port.outbound;

import com.omb.boat.model.Boat;

public interface BoatPersistencePort {

    Boat save(Boat boat);
}
