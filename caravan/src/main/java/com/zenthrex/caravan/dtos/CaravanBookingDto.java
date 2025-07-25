package com.zenthrex.caravan.dtos;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CaravanBookingDto {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double priceUnite;
    private Double assurancePrice;
    private String pickUp;
    private Double margin;
    private String status;
    private LocalDateTime createdOn;
    private Long caravanId;
}
