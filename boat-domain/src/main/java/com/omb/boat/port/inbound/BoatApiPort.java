package com.omb.boat.port.inbound;

import com.omb.boat.usecase.CreateBoatUseCase;
import com.omb.boat.usecase.DeleteBoatUseCase;
import com.omb.boat.usecase.GetBoatUseCase;
import com.omb.boat.usecase.UpdateBoatUseCase;

public interface BoatApiPort extends CreateBoatUseCase, UpdateBoatUseCase, DeleteBoatUseCase, GetBoatUseCase {
}
