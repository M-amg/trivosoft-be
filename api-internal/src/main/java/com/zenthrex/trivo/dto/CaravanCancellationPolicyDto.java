package com.zenthrex.trivo.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CaravanCancellationPolicyDto
 */
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Builder

@JsonTypeName("CaravanCancellationPolicy")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-07-22T21:39:07.033484200+01:00[Africa/Casablanca]")
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

    private String value;

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

  public CaravanCancellationPolicyDto id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public CaravanCancellationPolicyDto description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  */
  
  @Schema(name = "description", example = "Cancellation policy description", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public CaravanCancellationPolicyDto daysBefore(Integer daysBefore) {
    this.daysBefore = daysBefore;
    return this;
  }

  /**
   * Get daysBefore
   * @return daysBefore
  */
  
  @Schema(name = "daysBefore", example = "30", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("daysBefore")
  public Integer getDaysBefore() {
    return daysBefore;
  }

  public void setDaysBefore(Integer daysBefore) {
    this.daysBefore = daysBefore;
  }

  public CaravanCancellationPolicyDto penalty(Double penalty) {
    this.penalty = penalty;
    return this;
  }

  /**
   * Get penalty
   * @return penalty
  */
  
  @Schema(name = "penalty", example = "100.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("penalty")
  public Double getPenalty() {
    return penalty;
  }

  public void setPenalty(Double penalty) {
    this.penalty = penalty;
  }

  public CaravanCancellationPolicyDto penaltyType(PenaltyTypeEnum penaltyType) {
    this.penaltyType = penaltyType;
    return this;
  }

  /**
   * Get penaltyType
   * @return penaltyType
  */
  
  @Schema(name = "penaltyType", example = "FIXED", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("penaltyType")
  public PenaltyTypeEnum getPenaltyType() {
    return penaltyType;
  }

  public void setPenaltyType(PenaltyTypeEnum penaltyType) {
    this.penaltyType = penaltyType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CaravanCancellationPolicyDto caravanCancellationPolicy = (CaravanCancellationPolicyDto) o;
    return Objects.equals(this.id, caravanCancellationPolicy.id) &&
        Objects.equals(this.description, caravanCancellationPolicy.description) &&
        Objects.equals(this.daysBefore, caravanCancellationPolicy.daysBefore) &&
        Objects.equals(this.penalty, caravanCancellationPolicy.penalty) &&
        Objects.equals(this.penaltyType, caravanCancellationPolicy.penaltyType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, description, daysBefore, penalty, penaltyType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CaravanCancellationPolicyDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    daysBefore: ").append(toIndentedString(daysBefore)).append("\n");
    sb.append("    penalty: ").append(toIndentedString(penalty)).append("\n");
    sb.append("    penaltyType: ").append(toIndentedString(penaltyType)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

