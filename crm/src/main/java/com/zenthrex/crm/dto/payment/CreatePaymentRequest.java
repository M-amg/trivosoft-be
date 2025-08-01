package com.zenthrex.crm.dto.payment;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Schema(description = "Create payment request")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentRequest {

    @Schema(description = "Order ID (optional)")
    private Long orderId;

    @Schema(description = "Invoice ID (optional)")
    private Long invoiceId;

    @Schema(description = "Customer ID")
    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @Schema(description = "Payment amount")
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private BigDecimal amount;

    @Schema(description = "Currency")
    private String currency = "EUR";

    @Schema(description = "Payment method")
    @NotNull(message = "Payment method is required")
    private String paymentMethod;

    @Schema(description = "Reference number")
    private String referenceNumber;

    @Schema(description = "Payment notes")
    private String notes;

    @Schema(description = "Payment method details")
    @Valid
    private PaymentMethodDetailsDto paymentMethodDetails;
}
