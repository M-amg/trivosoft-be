package com.zenthrex.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Reject pro upgrade request")
public record RejectProUpgradeRequest(
        @Schema(description = "Rejection reason", example = "Insufficient documentation provided")
        @NotBlank(message = "Rejection reason is required")
        String reason,

        @Schema(description = "Additional notes")
        String notes
) {
}
