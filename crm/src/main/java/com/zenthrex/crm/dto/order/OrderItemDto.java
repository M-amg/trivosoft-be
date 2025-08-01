package com.zenthrex.crm.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "Order item information")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

    @Schema(description = "Order item ID")
    private Long id;

    @Schema(description = "Item type (CARAVAN, ACCESSORY, SERVICE)")
    private String itemType;

    @Schema(description = "Item ID reference")
    private Long itemId;

    @Schema(description = "Item name")
    private String itemName;

    @Schema(description = "Item description")
    private String itemDescription;

    @Schema(description = "Item SKU")
    private String itemSku;

    @Schema(description = "Quantity")
    @Positive(message = "Quantity must be positive")
    private Integer quantity;

    @Schema(description = "Unit price")
    @Positive(message = "Unit price must be positive")
    private BigDecimal unitPrice;

    @Schema(description = "Discount amount")
    private BigDecimal discountAmount;

    @Schema(description = "Tax amount")
    private BigDecimal taxAmount;

    @Schema(description = "Line total")
    private BigDecimal lineTotal;

    @Schema(description = "Start date for bookings")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startDate;

    @Schema(description = "End date for bookings")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endDate;

    @Schema(description = "Item notes")
    private String notes;

    @Schema(description = "Created date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;
}
