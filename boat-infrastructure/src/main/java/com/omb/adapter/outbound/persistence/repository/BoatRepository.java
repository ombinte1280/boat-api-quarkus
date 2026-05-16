package com.omb.adapter.outbound.persistence.repository;

import com.omb.adapter.outbound.persistence.entity.BoatEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class BoatRepository implements PanacheRepositoryBase<BoatEntity, UUID> {
}
