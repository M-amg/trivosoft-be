package com.zenthrex.caravan.mappers;

import com.zenthrex.core.dtos.FeaturesDto;
import com.zenthrex.core.entites.Features;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FeaturesMapper {

    Features toEntity(FeaturesDto featuresDto);

    FeaturesDto toDto(Features features);
}
