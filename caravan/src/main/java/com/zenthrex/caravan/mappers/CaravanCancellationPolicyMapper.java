package com.zenthrex.caravan.mappers;


import com.zenthrex.core.entites.caravan.CaravanStopSelling;
import com.zenthrex.trivo.dto.CaravanStopSellingDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CaravanCancellationPolicyMapper {
    @Mapping(target = "caravan", ignore = true)
    CaravanStopSelling toEntity(CaravanStopSellingDto caravanStopSellingDto);

    CaravanStopSellingDto toDto(CaravanStopSelling caravanStopSelling);}
