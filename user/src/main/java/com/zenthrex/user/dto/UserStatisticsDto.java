package com.zenthrex.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Map;

@Schema(description = "User statistics")
public record UserStatisticsDto(
        @Schema(description = "Total users count")
        Long totalUsers,

        @Schema(description = "Active users count")
        Long activeUsers,

        @Schema(description = "New users this period")
        Long newUsers,

        @Schema(description = "Users by role")
        Map<String, Long> usersByRole,

        @Schema(description = "Users by status")
        Map<String, Long> usersByStatus,

        @Schema(description = "Registration trend")
        List<StatisticPointDto> registrationTrend,

        @Schema(description = "Activity trend")
        List<StatisticPointDto> activityTrend,

        @Schema(description = "Top countries")
        List<CountryStatisticDto> topCountries,

        @Schema(description = "Verification statistics")
        VerificationStatisticsDto verificationStats
) {
}
