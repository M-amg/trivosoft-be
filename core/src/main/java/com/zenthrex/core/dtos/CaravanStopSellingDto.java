package com.zenthrex.core.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.annotation.Generated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CaravanStopSellingDto
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CaravanStopSellingDto {

  private Integer id;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate day;

  private String reason;


}

