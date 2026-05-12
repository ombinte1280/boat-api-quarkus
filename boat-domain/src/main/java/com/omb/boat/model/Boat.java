package com.omb.boat.model;

import java.time.LocalDate;
import java.util.UUID;

public record Boat(
        UUID id,
        String name,
        Category category,
        String description,
        String registration,
        LocalDate creationDate
) {
}
