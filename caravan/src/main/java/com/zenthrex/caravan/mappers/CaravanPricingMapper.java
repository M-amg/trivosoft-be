package com.zenthrex.caravan.mappers;

import com.zenthrex.core.dtos.CaravanPricingDto;
import com.zenthrex.core.entites.caravan.CaravanPricing;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CaravanPricingMapper {
    @Mapping(target = "caravan", ignore = true)
    CaravanPricing toEntity(CaravanPricingDto caravanPricingDto);

    CaravanPricingDto toDto(CaravanPricing caravanPricing);
}
