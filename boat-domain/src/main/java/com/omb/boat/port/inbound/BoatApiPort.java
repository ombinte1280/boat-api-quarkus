package com.omb.boat.port.inbound;

import com.omb.boat.usecase.SaveBoatUseCase;
import com.omb.boat.usecase.DeleteBoatUseCase;
import com.omb.boat.usecase.GetBoatUseCase;

public interface BoatApiPort extends SaveBoatUseCase, DeleteBoatUseCase, GetBoatUseCase {
}
