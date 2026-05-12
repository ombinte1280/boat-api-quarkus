package com.omb.adapter.outbound.persistence.mapper;

import com.omb.adapter.outbound.persistence.entity.BoatEntity;
import com.omb.boat.model.Boat;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface BoatMapper {

    BoatMapper INSTANCE = Mappers.getMapper(BoatMapper.class);

    Boat toDomain(BoatEntity boatEntity);
    BoatEntity toEntity(Boat boat);
    List<Boat> toDomain(List<BoatEntity> boatEntities);
    List<BoatEntity> toBoatEntity(List<Boat> boats);
}
