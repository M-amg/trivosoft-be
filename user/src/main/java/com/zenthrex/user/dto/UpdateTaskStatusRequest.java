package com.zenthrex.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Update task status request")
public record UpdateTaskStatusRequest(
        @Schema(description = "New task status")
        @NotBlank(message = "Task status is required")
        String status,

        @Schema(description = "Status update notes")
        String notes
) {
}
