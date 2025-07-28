package com.zenthrex.crm.mappers;

import com.zenthrex.core.entites.crm.ProcurementOrder;
import com.zenthrex.crm.dto.ProcurementOrderDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ProcurementOrderLineMapper.class)
public interface ProcurementOrderMapper {
    ProcurementOrderDto toDto(ProcurementOrder order);
    ProcurementOrder toEntity(ProcurementOrderDto dto);
}
