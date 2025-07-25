package com.zenthrex.caravan.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AvailableCaravanDto {
    private Integer id;
    private String title;
    private String brand;
    private String model;
    private Integer numberBed;
    private Double totalPrice;
}
