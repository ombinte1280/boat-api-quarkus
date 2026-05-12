package com.omb.boat.usecase;

import com.omb.boat.model.Boat;
import com.omb.boat.model.Category;

public interface CreateBoatUseCase {

    Boat createBoat(String name, Category category, String description, String registration);
}
