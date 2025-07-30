package com.zenthrex.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Update user role request")
public record UpdateUserRoleRequest(
        @Schema(description = "New role", example = "CLIENT_PRO", allowableValues = {"ADMIN", "AGENT", "CLIENT_PRO", "CLIENT_STANDARD"})
        @NotBlank(message = "Role is required")
        String role,

        @Schema(description = "Reason for role change")
        String reason
) {
}
