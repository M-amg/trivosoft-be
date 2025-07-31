package com.zenthrex.crm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Update order status request")
public record UpdateOrderStatusRequest(
        @Schema(description = "New status")
        @NotBlank(message = "Status is required")
        String status,

        @Schema(description = "Status update notes")
        String notes
) {
}
