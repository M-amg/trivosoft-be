package com.zenthrex.crm.mappers;

import com.zenthrex.core.entites.crm.ProcurementOrderLine;
import com.zenthrex.crm.dto.ProcurementOrderLineDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProcurementOrderLineMapper {
    ProcurementOrderLineDto toDto(ProcurementOrderLine line);
    ProcurementOrderLine toEntity(ProcurementOrderLineDto dto);
}
