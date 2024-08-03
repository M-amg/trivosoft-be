package com.zenthrex.trivo.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets RoleEnum
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-08-03T18:14:38.905050800+01:00[Africa/Casablanca]")
public enum RoleEnumDto {
  
  CUSTODIAN("CUSTODIAN"),
  
  ADMIN("ADMIN"),
  
  USER("USER");

  private String value;

  RoleEnumDto(String value) {
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
  public static RoleEnumDto fromValue(String value) {
    for (RoleEnumDto b : RoleEnumDto.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

