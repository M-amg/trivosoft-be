package com.zenthrex.crm.dto.payment;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Process payment request")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessPaymentRequest {

    @Schema(description = "Payment method")
    @NotNull(message = "Payment method is required")
    private String paymentMethod;

    @Schema(description = "Payment method details")
    @Valid
    @NotNull(message = "Payment method details are required")
    private PaymentMethodDetailsDto paymentMethodDetails;

    @Schema(description = "Process immediately")
    private Boolean processImmediately = true;

    @Schema(description = "Return URL (for redirects)")
    private String returnUrl;

    @Schema(description = "Cancel URL (for redirects)")
    private String cancelUrl;
}
