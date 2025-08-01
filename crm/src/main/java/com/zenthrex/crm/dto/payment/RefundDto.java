package com.zenthrex.crm.dto.payment;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Schema(description = "Refund information")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefundDto {

    @Schema(description = "Refund ID")
    private Long id;

    @Schema(description = "Refund number", example = "REF-2025-001234")
    private String refundNumber;

    @Schema(description = "Original payment information")
    @Valid
    private PaymentDto originalPayment;

    @Schema(description = "Related order ID")
    private Long orderId;

    @Schema(description = "Related invoice ID")
    private Long invoiceId;

    @Schema(description = "Customer information")
    @Valid
    private com.zenthrex.crm.dto.CustomerDto customer;

    @Schema(description = "Refund amount")
    private BigDecimal amount;

    @Schema(description = "Currency")
    private String currency;

    @Schema(description = "Refund status")
    private String status;

    @Schema(description = "Refund reason")
    private String reason;

    @Schema(description = "Refund notes")
    private String notes;

    @Schema(description = "Internal notes")
    private String internalNotes;

    @Schema(description = "Gateway refund ID")
    private String gatewayRefundId;

    @Schema(description = "Gateway response")
    private String gatewayResponse;

}
