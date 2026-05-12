package com.omb.adapter.inbound.rest.boat.dto;

import jdk.jfr.Category;

import java.time.LocalDate;
import java.util.UUID;

public record BoatResponse(
        UUID id,
        String name,
        Category category,
        String description,
        String registration,
        LocalDate creationDate
) {
}
