package com.zenthrex.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Disable two-factor authentication request")
public record DisableTwoFactorRequest(
        @Schema(description = "Current password")
        @NotBlank(message = "Current password is required")
        String currentPassword,

        @Schema(description = "Verification code or backup code")
        @NotBlank(message = "Verification code is required")
        String verificationCode
) {
}
