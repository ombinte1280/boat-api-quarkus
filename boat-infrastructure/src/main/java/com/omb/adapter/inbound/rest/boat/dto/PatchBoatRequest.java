package com.omb.adapter.inbound.rest.boat.dto;

import com.omb.boat.model.Category;
import com.omb.validation.AtLeastOneNotNull;

@AtLeastOneNotNull
public record PatchBoatRequest (
        String name,
        Category category,
        String registration,
        String description
){
}
