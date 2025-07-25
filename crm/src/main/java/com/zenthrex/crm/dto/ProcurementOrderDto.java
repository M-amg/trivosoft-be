package com.zenthrex.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcurementOrderDto {
    private Long id;
    private String number;
    private Double total;
    private Double vat;
    private Double margin;
    private String status;
    private LocalDateTime createdOn;
    private List<ProcurementOrderLineDto> procurementOrderLines;
}
