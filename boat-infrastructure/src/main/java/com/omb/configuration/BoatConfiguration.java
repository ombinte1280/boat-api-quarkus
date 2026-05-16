package com.omb.configuration;

import com.omb.boat.port.inbound.BoatApiPort;
import com.omb.boat.port.outbound.BoatPersistencePort;
import com.omb.boat.service.BoatDomainService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class BoatConfiguration {

    @Produces
    @ApplicationScoped
    public BoatApiPort boatApiPort(BoatPersistencePort boatPersistencePort) {
        return new BoatDomainService(boatPersistencePort);
    }
}
