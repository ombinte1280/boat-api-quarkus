package com.omb.adapter.inbound.rest.boat.dto;

import jakarta.validation.constraints.NotNull;

public record CreateBoatRequest(
        @NotNull
        String name,
        @NotNull
        String category,
        @NotNull
        String description,
        @NotNull
        String registration
) {
}
