package com.zenthrex.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcurementOrderLineDto {
    private Long id;
    private String designation;
    private Integer quantity;
    private Double unitPrice;
    private Double vat;
    private Double margin;
    private String entity;
    private Long entityId;
    private String status;
    private LocalDateTime createdOn;
}
