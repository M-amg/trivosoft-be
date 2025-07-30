package com.zenthrex.crm.dto;

import java.util.List;

@Schema(description = "Create order request")
public record CreateOrderRequest(
        @Schema(description = "Customer ID")
        @NotNull(message = "Customer ID is required")
        Long customerId,

        @Schema(description = "Order items")
        @NotEmpty(message = "Order must contain at least one item")
        @Valid
        List<OrderItemRequest> items,

        @Schema(description = "Delivery address")
        @Valid
        AddressDto deliveryAddress,

        @Schema(description = "Order notes")
        String notes,

        @Schema(description = "Payment method")
        String paymentMethod
) {
}
