package com.zenthrex.caravan.mappers;

import com.zenthrex.core.entites.Features;
import com.zenthrex.trivo.dto.FeaturesDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FeaturesMapper {

    Features toEntity(FeaturesDto featuresDto);

    FeaturesDto toDto(Features features);
}
