package com.zenthrex.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Schema(description = "Confirm two-factor authentication request")
public record ConfirmTwoFactorRequest(
        @Schema(description = "Verification code from authenticator app")
        @NotBlank(message = "Verification code is required")
        @Pattern(regexp = "^\\d{6}$", message = "Verification code must be 6 digits")
        String verificationCode
) {
}
