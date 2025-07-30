package com.zenthrex.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Schema(description = "Submit verification request")
public record SubmitVerificationRequest(
        @Schema(description = "Verification type")
        @NotBlank(message = "Verification type is required")
        String verificationType,

        @Schema(description = "Document IDs")
        @NotEmpty(message = "At least one document is required")
        List<Long> documentIds,

        @Schema(description = "Additional notes")
        String notes
) {
}
