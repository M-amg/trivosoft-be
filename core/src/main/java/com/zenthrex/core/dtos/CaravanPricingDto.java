package com.zenthrex.core.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CaravanPricingDto
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CaravanPricingDto {
  private Integer id;

  private Integer days;

  private Double price;

  private Double defaultPrice;

  private Double weekEndSupp;

  private Double limitKM;

  private Double kmPricing;

}

