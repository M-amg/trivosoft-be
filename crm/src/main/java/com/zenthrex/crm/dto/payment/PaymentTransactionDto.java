package com.zenthrex.crm.dto.payment;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "Payment transaction information")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTransactionDto {

    @Schema(description = "Transaction ID")
    private Long id;

    @Schema(description = "Transaction type (AUTHORIZE, CAPTURE, VOID, REFUND)")
    private String transactionType;

    @Schema(description = "Transaction amount")
    private BigDecimal amount;

    @Schema(description = "Transaction status")
    private String status;

    @Schema(description = "Gateway transaction ID")
    private String gatewayTransactionId;

    @Schema(description = "Gateway response")
    private String gatewayResponse;

    @Schema(description = "Error code")
    private String errorCode;

    @Schema(description = "Error message")
    private String errorMessage;

    @Schema(description = "Processed by user ID")
    private Long processedBy;

    @Schema(description = "Created date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;
}
