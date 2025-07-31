package com.zenthrex.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Pro upgrade request")
public record ProUpgradeRequestDto(
        @Schema(description = "Business name", example = "John's Caravan Rentals")
        @NotBlank(message = "Business name is required")

        @Size(min = 2, max = 100, message = "Business name must be between 2 and 100 characters")
        String businessName,

        @Schema(description = "Business type", example = "LLC")
        @NotBlank(message = "Business type is required")
        String businessType,

        @Schema(description = "Tax ID", example = "12-3456789")
        @NotBlank(message = "Tax ID is required")
        String taxId,

        @Schema(description = "Business address", example = "456 Business Ave")
        @NotBlank(message = "Business address is required")
        String businessAddress,

        @Schema(description = "Business phone", example = "+1987654321")
        @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number format")
        String businessPhone,

        @Schema(description = "Website URL", example = "https://johnscaravans.com")
        @Pattern(regexp = "^https?://.*", message = "Invalid URL format")
        String website,

        @Schema(description = "Years of experience", example = "5")
        @Min(value = 0, message = "Years of experience cannot be negative")
        Integer yearsOfExperience,

        @Schema(description = "Reason for upgrade", example = "Expanding business operations")
        @NotBlank(message = "Reason for upgrade is required")
        @Size(min = 10, max = 500, message = "Reason must be between 10 and 500 characters")
        String reason
) {
}
