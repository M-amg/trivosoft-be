package com.zenthrex.crm.dto;

@Schema(description = "Order item request")
public record OrderItemRequest(
        @Schema(description = "Item designation")
        @NotBlank(message = "Designation is required")
        String designation,

        @Schema(description = "Quantity")
        @NotNull(message = "Quantity is required")
        @Min(value = 1, message = "Quantity must be at least 1")
        Integer quantity,

        @Schema(description = "Unit price")
        @NotNull(message = "Unit price is required")
        @Min(value = 0, message = "Unit price must be positive")
        BigDecimal unitPrice,

        @Schema(description = "Margin amount")
        BigDecimal margin,

        @Schema(description = "Entity type (CARAVAN, ACCESSORY)")
        @NotBlank(message = "Entity type is required")
        String entityType,

        @Schema(description = "Entity ID")
        @NotNull(message = "Entity ID is required")
        Long entityId
) {
}
