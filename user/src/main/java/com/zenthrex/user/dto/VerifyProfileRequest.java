package com.zenthrex.user.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

// ==================== REQUEST DTOs ====================

@Schema(description = "Verify profile request")
public record VerifyProfileRequest(
        @Schema(description = "Verification decision")
        @NotNull(message = "Verification decision is required")
        Boolean verified,

        @Schema(description = "Verification notes", example = "Profile documents verified successfully")
        String notes
) {
}

// ==================== RESPONSE DTOs ====================

