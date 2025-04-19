package com.zenthrex.caravan.mappers;

import com.zenthrex.core.dtos.CaravanDto;
import com.zenthrex.core.entites.caravan.Caravan;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {
                DateMapper.class,
        })
public interface CaravanMapper {
    CaravanDto
    toDto(Caravan caravan);
    Caravan toEntity(CaravanDto caravanDto);
}
