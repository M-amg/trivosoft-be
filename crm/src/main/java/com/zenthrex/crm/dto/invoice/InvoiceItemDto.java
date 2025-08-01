package com.zenthrex.crm.dto.invoice;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "Invoice item information")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceItemDto {

    @Schema(description = "Invoice item ID")
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
    private Integer quantity;

    @Schema(description = "Unit price")
    private BigDecimal unitPrice;

    @Schema(description = "Discount amount")
    private BigDecimal discountAmount;

    @Schema(description = "Tax rate")
    private BigDecimal taxRate;

    @Schema(description = "Tax amount")
    private BigDecimal taxAmount;

    @Schema(description = "Line total")
    private BigDecimal lineTotal;

    @Schema(description = "Sort order")
    private Integer sortOrder;

    @Schema(description = "Created date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;
}
