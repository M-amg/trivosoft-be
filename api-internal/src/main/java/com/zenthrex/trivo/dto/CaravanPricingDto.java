package com.zenthrex.trivo.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CaravanPricingDto
 */
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Builder

@JsonTypeName("CaravanPricing")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-07-22T21:39:07.033484200+01:00[Africa/Casablanca]")
public class CaravanPricingDto {

  private Integer id;

  private Integer days;

  private Double price;

  private Double defaultPrice;

  private Double weekEndSupp;

  private Double limitKM;

  private Double kmPricing;

  public CaravanPricingDto id(Integer id) {
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

  public CaravanPricingDto days(Integer days) {
    this.days = days;
    return this;
  }

  /**
   * Get days
   * @return days
  */
  
  @Schema(name = "days", example = "7", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("days")
  public Integer getDays() {
    return days;
  }

  public void setDays(Integer days) {
    this.days = days;
  }

  public CaravanPricingDto price(Double price) {
    this.price = price;
    return this;
  }

  /**
   * Get price
   * @return price
  */
  
  @Schema(name = "price", example = "500.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("price")
  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public CaravanPricingDto defaultPrice(Double defaultPrice) {
    this.defaultPrice = defaultPrice;
    return this;
  }

  /**
   * Get defaultPrice
   * @return defaultPrice
  */
  
  @Schema(name = "defaultPrice", example = "450.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("defaultPrice")
  public Double getDefaultPrice() {
    return defaultPrice;
  }

  public void setDefaultPrice(Double defaultPrice) {
    this.defaultPrice = defaultPrice;
  }

  public CaravanPricingDto weekEndSupp(Double weekEndSupp) {
    this.weekEndSupp = weekEndSupp;
    return this;
  }

  /**
   * Get weekEndSupp
   * @return weekEndSupp
  */
  
  @Schema(name = "weekEndSupp", example = "50.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("weekEndSupp")
  public Double getWeekEndSupp() {
    return weekEndSupp;
  }

  public void setWeekEndSupp(Double weekEndSupp) {
    this.weekEndSupp = weekEndSupp;
  }

  public CaravanPricingDto limitKM(Double limitKM) {
    this.limitKM = limitKM;
    return this;
  }

  /**
   * Get limitKM
   * @return limitKM
  */
  
  @Schema(name = "limitKM", example = "1000.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("limitKM")
  public Double getLimitKM() {
    return limitKM;
  }

  public void setLimitKM(Double limitKM) {
    this.limitKM = limitKM;
  }

  public CaravanPricingDto kmPricing(Double kmPricing) {
    this.kmPricing = kmPricing;
    return this;
  }

  /**
   * Get kmPricing
   * @return kmPricing
  */
  
  @Schema(name = "kmPricing", example = "0.5", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("kmPricing")
  public Double getKmPricing() {
    return kmPricing;
  }

  public void setKmPricing(Double kmPricing) {
    this.kmPricing = kmPricing;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CaravanPricingDto caravanPricing = (CaravanPricingDto) o;
    return Objects.equals(this.id, caravanPricing.id) &&
        Objects.equals(this.days, caravanPricing.days) &&
        Objects.equals(this.price, caravanPricing.price) &&
        Objects.equals(this.defaultPrice, caravanPricing.defaultPrice) &&
        Objects.equals(this.weekEndSupp, caravanPricing.weekEndSupp) &&
        Objects.equals(this.limitKM, caravanPricing.limitKM) &&
        Objects.equals(this.kmPricing, caravanPricing.kmPricing);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, days, price, defaultPrice, weekEndSupp, limitKM, kmPricing);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CaravanPricingDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    days: ").append(toIndentedString(days)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    defaultPrice: ").append(toIndentedString(defaultPrice)).append("\n");
    sb.append("    weekEndSupp: ").append(toIndentedString(weekEndSupp)).append("\n");
    sb.append("    limitKM: ").append(toIndentedString(limitKM)).append("\n");
    sb.append("    kmPricing: ").append(toIndentedString(kmPricing)).append("\n");
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

