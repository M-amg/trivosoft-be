package com.zenthrex.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Statistic point for trends")
public record StatisticPointDto(
        @Schema(description = "Period label")
        String period,

        @Schema(description = "Statistic value")
        Number value,

        @Schema(description = "Change from previous period")
        Double changePercent
) {
}
