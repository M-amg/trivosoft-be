package com.zenthrex.core.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * FeaturesDto
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@JsonTypeName("Features")
public class FeaturesDto {

  private Integer id;

  private String title;

  private String icon;

  /**
   * Gets or Sets type
   */
  public enum TypeEnum
  {
    INTERIOR("INTERIOR"),
    
    EXTERIOR("EXTERIOR"),
    
    SAFETY("SAFETY");

    private final String value;

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


}

