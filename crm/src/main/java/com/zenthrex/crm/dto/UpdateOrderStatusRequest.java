package com.zenthrex.crm.dto;

@Schema(description = "Update order status request")
public record UpdateOrderStatusRequest(
        @Schema(description = "New status")
        @NotBlank(message = "Status is required")
        String status,

        @Schema(description = "Status update notes")
        String notes
) {
}
