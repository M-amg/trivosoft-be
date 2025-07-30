package com.zenthrex.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Verification statistics")
public record VerificationStatisticsDto(
        @Schema(description = "Verified profiles count")
        Long verifiedProfiles,

        @Schema(description = "Pending verifications")
        Long pendingVerifications,

        @Schema(description = "Verification rate")
        Double verificationRate,

        @Schema(description = "Average verification time in days")
        Double averageVerificationTime
) {
}
