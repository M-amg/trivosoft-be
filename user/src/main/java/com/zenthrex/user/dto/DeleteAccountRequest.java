package com.zenthrex.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Delete account request")
public record DeleteAccountRequest(
        @Schema(description = "Current password")
        @NotBlank(message = "Current password is required")
        String currentPassword,

        @Schema(description = "Deletion reason")
        @NotBlank(message = "Deletion reason is required")
        String reason,

        @Schema(description = "Additional feedback")
        String feedback,

        @Schema(description = "Confirm deletion")
        @NotNull(message = "Deletion confirmation is required")
        Boolean confirmDeletion
) {
}
