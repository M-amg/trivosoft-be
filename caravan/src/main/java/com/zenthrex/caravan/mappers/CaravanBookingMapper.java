package com.zenthrex.caravan.mappers;

import com.zenthrex.caravan.dtos.CaravanBookingDto;
import com.zenthrex.core.entites.caravan.CaravanBooking;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CaravanBookingMapper {

    @Mapping(source = "caravan.id", target = "caravanId")
    CaravanBookingDto toDto(CaravanBooking entity);

    @Mapping(source = "caravanId", target = "caravan.id")
    CaravanBooking toEntity(CaravanBookingDto dto);
}
