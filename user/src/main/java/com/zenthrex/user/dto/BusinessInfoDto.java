package com.zenthrex.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Business information")
public record BusinessInfoDto(
        @Schema(description = "Business name")
        String businessName,

        @Schema(description = "Business type")
        String businessType,

        @Schema(description = "Tax ID")
        String taxId,

        @Schema(description = "Business address")
        String businessAddress,

        @Schema(description = "Business phone")
        String businessPhone,

        @Schema(description = "Website URL")
        String website,

        @Schema(description = "Years of experience")
        Integer yearsOfExperience
) {
}
