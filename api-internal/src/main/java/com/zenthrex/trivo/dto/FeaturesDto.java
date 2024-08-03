package com.zenthrex.trivo.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
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
 * FeaturesDto
 */

@JsonTypeName("Features")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-03T18:14:38.905050800+01:00[Africa/Casablanca]")
public class FeaturesDto {

  private Integer id;

  private String title;

  private String icon;

  /**
   * Gets or Sets type
   */
  public enum TypeEnum {
    INTERIOR("INTERIOR"),
    
    EXTERIOR("EXTERIOR"),
    
    SAFETY("SAFETY");

    private String value;

    TypeEnum(String value) {
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
    public static TypeEnum fromValue(String value) {
      for (TypeEnum b : TypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private TypeEnum type;

  @Valid
  private List<String> entities;

  public FeaturesDto id(Integer id) {
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

  public FeaturesDto title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  */
  
  @Schema(name = "title", example = "Air Conditioning", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public FeaturesDto icon(String icon) {
    this.icon = icon;
    return this;
  }

  /**
   * Get icon
   * @return icon
  */
  
  @Schema(name = "icon", example = "ac_icon.png", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("icon")
  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public FeaturesDto type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  */
  
  @Schema(name = "type", example = "INTERIOR", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("type")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public FeaturesDto entities(List<String> entities) {
    this.entities = entities;
    return this;
  }

  public FeaturesDto addEntitiesItem(String entitiesItem) {
    if (this.entities == null) {
      this.entities = new ArrayList<>();
    }
    this.entities.add(entitiesItem);
    return this;
  }

  /**
   * Get entities
   * @return entities
  */
  
  @Schema(name = "entities", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("entities")
  public List<String> getEntities() {
    return entities;
  }

  public void setEntities(List<String> entities) {
    this.entities = entities;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FeaturesDto features = (FeaturesDto) o;
    return Objects.equals(this.id, features.id) &&
        Objects.equals(this.title, features.title) &&
        Objects.equals(this.icon, features.icon) &&
        Objects.equals(this.type, features.type) &&
        Objects.equals(this.entities, features.entities);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, icon, type, entities);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FeaturesDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    icon: ").append(toIndentedString(icon)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    entities: ").append(toIndentedString(entities)).append("\n");
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

