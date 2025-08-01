package com.zenthrex.crm.dto.payment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Schema(description = "Capture payment request")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CapturePaymentRequest {

    @Schema(description = "Amount to capture (optional, captures full amount if not specified)")
    private BigDecimal amount;

    @Schema(description = "Capture notes")
    private String notes;
}
