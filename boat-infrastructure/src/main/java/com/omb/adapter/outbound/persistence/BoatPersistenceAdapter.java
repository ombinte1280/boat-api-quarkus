package com.omb.adapter.outbound.persistence;

import com.omb.adapter.outbound.persistence.entity.BoatEntity;
import com.omb.adapter.outbound.persistence.mapper.BoatMapper;
import com.omb.adapter.outbound.persistence.repository.BoatRepository;
import com.omb.boat.model.Boat;
import com.omb.boat.port.outbound.BoatPersistencePort;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class BoatPersistenceAdapter implements BoatPersistencePort {

    private final BoatRepository boatRepository;
    private final BoatMapper boatMapper;

    @Override
    public Boat save(Boat boat) {
        BoatEntity entity = boatMapper.toEntity(boat);
        boatRepository.persist(entity);
        return boatMapper.toDomain(entity);
    }
}
