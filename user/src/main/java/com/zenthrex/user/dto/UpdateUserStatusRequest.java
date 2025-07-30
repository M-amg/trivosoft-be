package com.zenthrex.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Update user status request")
public record UpdateUserStatusRequest(
        @Schema(description = "New status", example = "ACTIVE", allowableValues = {"ACTIVE", "INACTIVE", "SUSPENDED"})
        @NotBlank(message = "Status is required")
        String status,

        @Schema(description = "Reason for status change", example = "Account verified")
        String reason
) {
}
