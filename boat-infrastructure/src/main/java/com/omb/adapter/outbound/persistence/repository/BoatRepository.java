package com.omb.adapter.outbound.persistence.repository;

import com.omb.adapter.outbound.persistence.entity.BoatEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BoatRepository implements PanacheRepository<BoatEntity> {
}
