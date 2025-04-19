package com.zenthrex.core.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * CaravanDto
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CaravanDto {

  private Integer id;

  private String title;

  private String description;

  private String vin;

  /**
   * Gets or Sets category
   */
  public enum CategoryEnum {
    SMALL("SMALL"),
    
    MEDIUM("MEDIUM"),
    
    LARGE("LARGE");

    private String value;

    CategoryEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static CategoryEnum fromValue(String value) {
      for (CategoryEnum b : CategoryEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private CategoryEnum category;

  private String brand;

  private String model;

  private Integer year;

  private Double height;

  private Double weight;

  private Boolean canMove;

  private Integer numberBed;

  private Double originalPrice;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    AVAILABLE("AVAILABLE"),
    
    UNAVAILABLE("UNAVAILABLE");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String value) {
      for (StatusEnum b : StatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private StatusEnum status;

  private Double latitude;

  private Double longitude;

  private Boolean isImmediatelyBooked;

  private Double assuranceAmount;

  @Valid
  private List<@Valid CaravanPricingDto> caravanPrices;

  @Valid
  private List<@Valid FeaturesDto> features;

  @Valid
  private List<@Valid CaravanStopSellingDto> stopSells;

  @Valid
  private List<@Valid CaravanCancellationPolicyDto> cancellationPolicies;

  private UserDto user;

  public CaravanDto id(Integer id) {
    this.id = id;
    return this;
  }


}

