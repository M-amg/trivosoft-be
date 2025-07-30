package com.zenthrex.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Suspend user request")
public record SuspendUserRequest(
        @Schema(description = "Suspension reason", example = "Policy violation")
        @NotBlank(message = "Suspension reason is required")
        String reason,

        @Schema(description = "Suspension duration in days", example = "30")
        @Min(value = 1, message = "Suspension duration must be at least 1 day")
        @Max(value = 365, message = "Suspension duration cannot exceed 365 days")
        Integer durationDays,

        @Schema(description = "Additional notes")
        String notes
) {
}
