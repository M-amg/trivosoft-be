// ==================== ORDER MANAGEMENT DTOs ====================

package com.zenthrex.crm.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "Order information")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    @Schema(description = "Order ID")
    private Long id;

    @Schema(description = "Order number", example = "ORD-2025-001234")
    private String orderNumber;

    @Schema(description = "Order type")
    private String orderType;

    @Schema(description = "Order status")
    private String status;

    @Schema(description = "Customer information")
    @Valid
    private CustomerDto customer;

    @Schema(description = "Customer email")
    private String customerEmail;

    @Schema(description = "Customer phone")
    private String customerPhone;

    @Schema(description = "Subtotal amount")
    private BigDecimal subtotal;

    @Schema(description = "Tax amount")
    private BigDecimal taxAmount;

    @Schema(description = "Discount amount")
    private BigDecimal discountAmount;

    @Schema(description = "Shipping amount")
    private BigDecimal shippingAmount;

    @Schema(description = "Total amount")
    private BigDecimal totalAmount;

    @Schema(description = "Currency")
    private String currency;

    @Schema(description = "Order notes")
    private String notes;

    @Schema(description = "Internal notes")
    private String internalNotes;

    @Schema(description = "Billing address")
    @Valid
    private AddressDto billingAddress;

    @Schema(description = "Shipping address")
    @Valid
    private AddressDto shippingAddress;

    @Schema(description = "Expected delivery date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime expectedDeliveryDate;

    @Schema(description = "Actual delivery date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime actualDeliveryDate;

    @Schema(description = "Order items")
    @Valid
    private List<@Valid OrderItemDto> orderItems;

    @Schema(description = "Order status history")
    @Valid
    private List<@Valid OrderStatusHistoryDto> statusHistory;

    @Schema(description = "Created date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @Schema(description = "Updated date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;

    @Schema(description = "Created by user ID")
    private Long createdBy;

    @Schema(description = "Updated by user ID")
    private Long updatedBy;
}

// ==================== ORDER REQUEST DTOs ====================

