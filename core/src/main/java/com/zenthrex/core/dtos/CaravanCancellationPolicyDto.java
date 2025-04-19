package com.zenthrex.core.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CaravanCancellationPolicyDto
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CaravanCancellationPolicyDto {

  private Integer id;

  private String description;

  private Integer daysBefore;

  private Double penalty;

  /**
   * Gets or Sets penaltyType
   */
  public enum PenaltyTypeEnum {
    FIXED("FIXED"),
    
    PERCENTAGE("PERCENTAGE");

    private final String value;

    PenaltyTypeEnum(String value) {
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
    public static PenaltyTypeEnum fromValue(String value) {
      for (PenaltyTypeEnum b : PenaltyTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private PenaltyTypeEnum penaltyType;

}

