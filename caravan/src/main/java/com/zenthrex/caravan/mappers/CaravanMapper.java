package com.zenthrex.caravan.mappers;

import com.zenthrex.caravan.mappers.resolver.CaravanMapperResolver;
import com.zenthrex.core.entites.caravan.Caravan;
import com.zenthrex.trivo.dto.CaravanDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {
                DateMapper.class,
                CaravanMapperResolver.class,
        })
public interface CaravanMapper {
    CaravanDto toDto(Caravan caravan);
    Caravan toEntity(CaravanDto caravanDto);
}
