package com.zenthrex.trivo.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CaravanStopSellingDto
 */
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Builder

@JsonTypeName("CaravanStopSelling")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-07-22T21:39:07.033484200+01:00[Africa/Casablanca]")
public class CaravanStopSellingDto {

  private Integer id;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate day;

  private String reason;

  public CaravanStopSellingDto id(Integer id) {
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

  public CaravanStopSellingDto day(LocalDate day) {
    this.day = day;
    return this;
  }

  /**
   * Get day
   * @return day
  */
  @Valid 
  @Schema(name = "day", example = "Mon Jul 22 01:00:00 WEST 2024", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("day")
  public LocalDate getDay() {
    return day;
  }

  public void setDay(LocalDate day) {
    this.day = day;
  }

  public CaravanStopSellingDto reason(String reason) {
    this.reason = reason;
    return this;
  }

  /**
   * Get reason
   * @return reason
  */
  
  @Schema(name = "reason", example = "Maintenance", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("reason")
  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CaravanStopSellingDto caravanStopSelling = (CaravanStopSellingDto) o;
    return Objects.equals(this.id, caravanStopSelling.id) &&
        Objects.equals(this.day, caravanStopSelling.day) &&
        Objects.equals(this.reason, caravanStopSelling.reason);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, day, reason);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CaravanStopSellingDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    day: ").append(toIndentedString(day)).append("\n");
    sb.append("    reason: ").append(toIndentedString(reason)).append("\n");
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

