package com.zenthrex.trivo.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.zenthrex.trivo.dto.CaravanCancellationPolicyDto;
import com.zenthrex.trivo.dto.CaravanPricingDto;
import com.zenthrex.trivo.dto.CaravanStopSellingDto;
import com.zenthrex.trivo.dto.FeaturesDto;
import com.zenthrex.trivo.dto.UserDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CaravanDto
 */

@JsonTypeName("Caravan")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-03T18:14:38.905050800+01:00[Africa/Casablanca]")
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

  public CaravanDto title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  */
  
  @Schema(name = "title", example = "Luxury Caravan", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public CaravanDto description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  */
  
  @Schema(name = "description", example = "A luxury caravan with all modern amenities.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public CaravanDto vin(String vin) {
    this.vin = vin;
    return this;
  }

  /**
   * Get vin
   * @return vin
  */
  
  @Schema(name = "vin", example = "1HGCM82633A123456", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("vin")
  public String getVin() {
    return vin;
  }

  public void setVin(String vin) {
    this.vin = vin;
  }

  public CaravanDto category(CategoryEnum category) {
    this.category = category;
    return this;
  }

  /**
   * Get category
   * @return category
  */
  
  @Schema(name = "category", example = "MEDIUM", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("category")
  public CategoryEnum getCategory() {
    return category;
  }

  public void setCategory(CategoryEnum category) {
    this.category = category;
  }

  public CaravanDto brand(String brand) {
    this.brand = brand;
    return this;
  }

  /**
   * Get brand
   * @return brand
  */
  
  @Schema(name = "brand", example = "CaravanBrand", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("brand")
  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public CaravanDto model(String model) {
    this.model = model;
    return this;
  }

  /**
   * Get model
   * @return model
  */
  
  @Schema(name = "model", example = "ModelX", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("model")
  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public CaravanDto year(Integer year) {
    this.year = year;
    return this;
  }

  /**
   * Get year
   * @return year
  */
  
  @Schema(name = "year", example = "2021", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("year")
  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public CaravanDto height(Double height) {
    this.height = height;
    return this;
  }

  /**
   * Get height
   * @return height
  */
  
  @Schema(name = "height", example = "3.5", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("height")
  public Double getHeight() {
    return height;
  }

  public void setHeight(Double height) {
    this.height = height;
  }

  public CaravanDto weight(Double weight) {
    this.weight = weight;
    return this;
  }

  /**
   * Get weight
   * @return weight
  */
  
  @Schema(name = "weight", example = "1500.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("weight")
  public Double getWeight() {
    return weight;
  }

  public void setWeight(Double weight) {
    this.weight = weight;
  }

  public CaravanDto canMove(Boolean canMove) {
    this.canMove = canMove;
    return this;
  }

  /**
   * Get canMove
   * @return canMove
  */
  
  @Schema(name = "canMove", example = "true", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("canMove")
  public Boolean getCanMove() {
    return canMove;
  }

  public void setCanMove(Boolean canMove) {
    this.canMove = canMove;
  }

  public CaravanDto numberBed(Integer numberBed) {
    this.numberBed = numberBed;
    return this;
  }

  /**
   * Get numberBed
   * @return numberBed
  */
  
  @Schema(name = "numberBed", example = "4", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("numberBed")
  public Integer getNumberBed() {
    return numberBed;
  }

  public void setNumberBed(Integer numberBed) {
    this.numberBed = numberBed;
  }

  public CaravanDto originalPrice(Double originalPrice) {
    this.originalPrice = originalPrice;
    return this;
  }

  /**
   * Get originalPrice
   * @return originalPrice
  */
  
  @Schema(name = "originalPrice", example = "30000.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("originalPrice")
  public Double getOriginalPrice() {
    return originalPrice;
  }

  public void setOriginalPrice(Double originalPrice) {
    this.originalPrice = originalPrice;
  }

  public CaravanDto status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  
  @Schema(name = "status", example = "AVAILABLE", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("status")
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public CaravanDto latitude(Double latitude) {
    this.latitude = latitude;
    return this;
  }

  /**
   * Get latitude
   * @return latitude
  */
  
  @Schema(name = "latitude", example = "34.052235", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("latitude")
  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public CaravanDto longitude(Double longitude) {
    this.longitude = longitude;
    return this;
  }

  /**
   * Get longitude
   * @return longitude
  */
  
  @Schema(name = "longitude", example = "-118.243683", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("longitude")
  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public CaravanDto isImmediatelyBooked(Boolean isImmediatelyBooked) {
    this.isImmediatelyBooked = isImmediatelyBooked;
    return this;
  }

  /**
   * Get isImmediatelyBooked
   * @return isImmediatelyBooked
  */
  
  @Schema(name = "isImmediatelyBooked", example = "false", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("isImmediatelyBooked")
  public Boolean getIsImmediatelyBooked() {
    return isImmediatelyBooked;
  }

  public void setIsImmediatelyBooked(Boolean isImmediatelyBooked) {
    this.isImmediatelyBooked = isImmediatelyBooked;
  }

  public CaravanDto assuranceAmount(Double assuranceAmount) {
    this.assuranceAmount = assuranceAmount;
    return this;
  }

  /**
   * Get assuranceAmount
   * @return assuranceAmount
  */
  
  @Schema(name = "assuranceAmount", example = "500.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("assuranceAmount")
  public Double getAssuranceAmount() {
    return assuranceAmount;
  }

  public void setAssuranceAmount(Double assuranceAmount) {
    this.assuranceAmount = assuranceAmount;
  }

  public CaravanDto caravanPrices(List<@Valid CaravanPricingDto> caravanPrices) {
    this.caravanPrices = caravanPrices;
    return this;
  }

  public CaravanDto addCaravanPricesItem(CaravanPricingDto caravanPricesItem) {
    if (this.caravanPrices == null) {
      this.caravanPrices = new ArrayList<>();
    }
    this.caravanPrices.add(caravanPricesItem);
    return this;
  }

  /**
   * Get caravanPrices
   * @return caravanPrices
  */
  @Valid 
  @Schema(name = "caravanPrices", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("caravanPrices")
  public List<@Valid CaravanPricingDto> getCaravanPrices() {
    return caravanPrices;
  }

  public void setCaravanPrices(List<@Valid CaravanPricingDto> caravanPrices) {
    this.caravanPrices = caravanPrices;
  }

  public CaravanDto features(List<@Valid FeaturesDto> features) {
    this.features = features;
    return this;
  }

  public CaravanDto addFeaturesItem(FeaturesDto featuresItem) {
    if (this.features == null) {
      this.features = new ArrayList<>();
    }
    this.features.add(featuresItem);
    return this;
  }

  /**
   * Get features
   * @return features
  */
  @Valid 
  @Schema(name = "features", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("features")
  public List<@Valid FeaturesDto> getFeatures() {
    return features;
  }

  public void setFeatures(List<@Valid FeaturesDto> features) {
    this.features = features;
  }

  public CaravanDto stopSells(List<@Valid CaravanStopSellingDto> stopSells) {
    this.stopSells = stopSells;
    return this;
  }

  public CaravanDto addStopSellsItem(CaravanStopSellingDto stopSellsItem) {
    if (this.stopSells == null) {
      this.stopSells = new ArrayList<>();
    }
    this.stopSells.add(stopSellsItem);
    return this;
  }

  /**
   * Get stopSells
   * @return stopSells
  */
  @Valid 
  @Schema(name = "stopSells", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("stopSells")
  public List<@Valid CaravanStopSellingDto> getStopSells() {
    return stopSells;
  }

  public void setStopSells(List<@Valid CaravanStopSellingDto> stopSells) {
    this.stopSells = stopSells;
  }

  public CaravanDto cancellationPolicies(List<@Valid CaravanCancellationPolicyDto> cancellationPolicies) {
    this.cancellationPolicies = cancellationPolicies;
    return this;
  }

  public CaravanDto addCancellationPoliciesItem(CaravanCancellationPolicyDto cancellationPoliciesItem) {
    if (this.cancellationPolicies == null) {
      this.cancellationPolicies = new ArrayList<>();
    }
    this.cancellationPolicies.add(cancellationPoliciesItem);
    return this;
  }

  /**
   * Get cancellationPolicies
   * @return cancellationPolicies
  */
  @Valid 
  @Schema(name = "cancellationPolicies", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("cancellationPolicies")
  public List<@Valid CaravanCancellationPolicyDto> getCancellationPolicies() {
    return cancellationPolicies;
  }

  public void setCancellationPolicies(List<@Valid CaravanCancellationPolicyDto> cancellationPolicies) {
    this.cancellationPolicies = cancellationPolicies;
  }

  public CaravanDto user(UserDto user) {
    this.user = user;
    return this;
  }

  /**
   * Get user
   * @return user
  */
  @Valid 
  @Schema(name = "user", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("user")
  public UserDto getUser() {
    return user;
  }

  public void setUser(UserDto user) {
    this.user = user;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CaravanDto caravan = (CaravanDto) o;
    return Objects.equals(this.id, caravan.id) &&
        Objects.equals(this.title, caravan.title) &&
        Objects.equals(this.description, caravan.description) &&
        Objects.equals(this.vin, caravan.vin) &&
        Objects.equals(this.category, caravan.category) &&
        Objects.equals(this.brand, caravan.brand) &&
        Objects.equals(this.model, caravan.model) &&
        Objects.equals(this.year, caravan.year) &&
        Objects.equals(this.height, caravan.height) &&
        Objects.equals(this.weight, caravan.weight) &&
        Objects.equals(this.canMove, caravan.canMove) &&
        Objects.equals(this.numberBed, caravan.numberBed) &&
        Objects.equals(this.originalPrice, caravan.originalPrice) &&
        Objects.equals(this.status, caravan.status) &&
        Objects.equals(this.latitude, caravan.latitude) &&
        Objects.equals(this.longitude, caravan.longitude) &&
        Objects.equals(this.isImmediatelyBooked, caravan.isImmediatelyBooked) &&
        Objects.equals(this.assuranceAmount, caravan.assuranceAmount) &&
        Objects.equals(this.caravanPrices, caravan.caravanPrices) &&
        Objects.equals(this.features, caravan.features) &&
        Objects.equals(this.stopSells, caravan.stopSells) &&
        Objects.equals(this.cancellationPolicies, caravan.cancellationPolicies) &&
        Objects.equals(this.user, caravan.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, description, vin, category, brand, model, year, height, weight, canMove, numberBed, originalPrice, status, latitude, longitude, isImmediatelyBooked, assuranceAmount, caravanPrices, features, stopSells, cancellationPolicies, user);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CaravanDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    vin: ").append(toIndentedString(vin)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    brand: ").append(toIndentedString(brand)).append("\n");
    sb.append("    model: ").append(toIndentedString(model)).append("\n");
    sb.append("    year: ").append(toIndentedString(year)).append("\n");
    sb.append("    height: ").append(toIndentedString(height)).append("\n");
    sb.append("    weight: ").append(toIndentedString(weight)).append("\n");
    sb.append("    canMove: ").append(toIndentedString(canMove)).append("\n");
    sb.append("    numberBed: ").append(toIndentedString(numberBed)).append("\n");
    sb.append("    originalPrice: ").append(toIndentedString(originalPrice)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
    sb.append("    longitude: ").append(toIndentedString(longitude)).append("\n");
    sb.append("    isImmediatelyBooked: ").append(toIndentedString(isImmediatelyBooked)).append("\n");
    sb.append("    assuranceAmount: ").append(toIndentedString(assuranceAmount)).append("\n");
    sb.append("    caravanPrices: ").append(toIndentedString(caravanPrices)).append("\n");
    sb.append("    features: ").append(toIndentedString(features)).append("\n");
    sb.append("    stopSells: ").append(toIndentedString(stopSells)).append("\n");
    sb.append("    cancellationPolicies: ").append(toIndentedString(cancellationPolicies)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
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

