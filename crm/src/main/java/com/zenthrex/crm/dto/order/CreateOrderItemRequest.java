package com.zenthrex.crm.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "Create order item request")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderItemRequest {

    @Schema(description = "Item type")
    @NotNull(message = "Item type is required")
    private String itemType;

    @Schema(description = "Item ID")
    @NotNull(message = "Item ID is required")
    private Long itemId;

    @Schema(description = "Quantity")
    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be positive")
    private Integer quantity;

    @Schema(description = "Unit price")
    @NotNull(message = "Unit price is required")
    @Positive(message = "Unit price must be positive")
    private BigDecimal unitPrice;

    @Schema(description = "Discount amount")
    private BigDecimal discountAmount = BigDecimal.ZERO;

    @Schema(description = "Start date for bookings")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startDate;

    @Schema(description = "End date for bookings")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endDate;

    @Schema(description = "Item notes")
    private String notes;
}
