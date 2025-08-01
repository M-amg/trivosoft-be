package com.zenthrex.crm.dto.payment;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Void payment request")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoidPaymentRequest {

    @Schema(description = "Void reason")
    @NotNull(message = "Void reason is required")
    private String reason;

    @Schema(description = "Additional notes")
    private String notes;
}
