package com.zenthrex.crm.dto.payment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Schema(description = "Payment method details")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodDetailsDto {

    @Schema(description = "Card token (for card payments)")
    private String cardToken;

    @Schema(description = "Bank account details (for bank transfers)")
    private String bankAccount;

    @Schema(description = "PayPal email (for PayPal payments)")
    private String paypalEmail;

    @Schema(description = "Additional payment data")
    private Map<String, Object> additionalData;
}
