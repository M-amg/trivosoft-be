package com.zenthrex.crm.dto;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Order information")
public class OrderDto {

    @Schema(description = "Order ID")
    private Long id;

    @Schema(description = "Order number")
    private String number;

    @Schema(description = "Order total amount")
    private BigDecimal total;

    @Schema(description = "VAT amount")
    private BigDecimal vat;

    @Schema(description = "Margin amount")
    private BigDecimal margin;

    @Schema(description = "Order status")
    private String status;

    @Schema(description = "Customer information")
    private CustomerDto customer;

    @Schema(description = "Order items")
    private List<OrderLineDto> orderLines;

    @Schema(description = "Payment information")
    private List<PaymentDto> payments;

    @Schema(description = "Order creation date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdOn;

    @Schema(description = "Delivery address")
    private AddressDto deliveryAddress;

    @Schema(description = "Order notes")
    private String notes;
}
