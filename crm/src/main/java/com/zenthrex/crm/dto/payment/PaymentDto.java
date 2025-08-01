package com.zenthrex.crm.dto.payment;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.zenthrex.crm.dto.CustomerDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "Payment information")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {

    @Schema(description = "Payment ID")
    private Long id;

    @Schema(description = "Payment number", example = "PAY-2025-001234")
    private String paymentNumber;

    @Schema(description = "Related order ID")
    private Long orderId;

    @Schema(description = "Related invoice ID")
    private Long invoiceId;

    @Schema(description = "Customer information")
    @Valid
    private CustomerDto customer;

    @Schema(description = "Payment amount")
    private BigDecimal amount;

    @Schema(description = "Currency")
    private String currency;

    @Schema(description = "Payment method")
    private String paymentMethod;

    @Schema(description = "Payment status")
    private String status;

    @Schema(description = "Reference number")
    private String referenceNumber;

    @Schema(description = "Transaction ID")
    private String transactionId;

    @Schema(description = "Gateway response")
    private String gatewayResponse;

    @Schema(description = "Gateway reference")
    private String gatewayReference;

    @Schema(description = "Processing fee")
    private BigDecimal processingFee;

    @Schema(description = "Net amount (after fees)")
    private BigDecimal netAmount;

    @Schema(description = "Payment notes")
    private String notes;

    @Schema(description = "Internal notes")
    private String internalNotes;

    @Schema(description = "Processed date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime processedAt;

    @Schema(description = "Processed by user ID")
    private Long processedBy;

    @Schema(description = "Payment transactions")
    @Valid
    private List<@Valid PaymentTransactionDto> transactions;

    @Schema(description = "Refunds")
    @Valid
    private List<@Valid RefundDto> refunds;

    @Schema(description = "Created date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @Schema(description = "Updated date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;
}



