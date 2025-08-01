package com.zenthrex.crm.dto.invoice;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Schema(description = "Create invoice item request")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvoiceItemRequest {

    @Schema(description = "Item type")
    @NotNull(message = "Item type is required")
    private String itemType;

    @Schema(description = "Item ID")
    private Long itemId;

    @Schema(description = "Item name")
    @NotNull(message = "Item name is required")
    private String itemName;

    @Schema(description = "Item description")
    private String itemDescription;

    @Schema(description = "Item SKU")
    private String itemSku;

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

    @Schema(description = "Tax rate")
    private BigDecimal taxRate = new BigDecimal("0.20"); // 20% VAT

    @Schema(description = "Sort order")
    private Integer sortOrder = 0;
}
