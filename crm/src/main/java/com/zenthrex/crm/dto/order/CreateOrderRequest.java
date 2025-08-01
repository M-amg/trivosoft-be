package com.zenthrex.crm.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "Create order request")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {

    @Schema(description = "Customer ID")
    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @Schema(description = "Order type")
    @NotNull(message = "Order type is required")
    private String orderType;

    @Schema(description = "Order items")
    @Valid
    @NotNull(message = "Order items are required")
    private List<@Valid CreateOrderItemRequest> items;

    @Schema(description = "Billing address")
    @Valid
    private CreateAddressRequest billingAddress;

    @Schema(description = "Shipping address")
    @Valid
    private CreateAddressRequest shippingAddress;

    @Schema(description = "Order notes")
    private String notes;

    @Schema(description = "Payment method")
    private String paymentMethod;

    @Schema(description = "Currency")
    private String currency = "EUR";
}
