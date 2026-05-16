package com.omb.boat.service;

import com.omb.boat.model.Boat;
import com.omb.boat.port.inbound.BoatApiPort;
import com.omb.boat.port.outbound.BoatPersistencePort;
import com.omb.exception.BusinessException;

import java.util.List;
import java.util.UUID;

public class BoatDomainService implements BoatApiPort {

    private final BoatPersistencePort persistencePort;

    public BoatDomainService(BoatPersistencePort persistencePort) {
        this.persistencePort = persistencePort;
    }

    @Override
    public Boat saveBoat(Boat boat) {
        return persistencePort.save(boat);
    }

    @Override
    public void deleteBoat(UUID id) {
        persistencePort.findById(id)
                .orElseThrow(() -> new BusinessException("Bateau non trouvé : " + id.toString()));
        persistencePort.deleteById(id);
    }

    @Override
    public Boat getBoat(UUID id) {
        return persistencePort.findById(id)
                .orElseThrow(() -> new BusinessException("Bateau non trouvé : " + id.toString()));
    }

    @Override
    public List<Boat> getBoats() {
        return persistencePort.findAll();
    }

}
