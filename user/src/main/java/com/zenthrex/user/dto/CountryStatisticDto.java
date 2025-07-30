package com.zenthrex.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Country statistic")
public record CountryStatisticDto(
        @Schema(description = "Country name")
        String country,

        @Schema(description = "User count")
        Long userCount,

        @Schema(description = "Percentage of total")
        Double percentage
) {
}
