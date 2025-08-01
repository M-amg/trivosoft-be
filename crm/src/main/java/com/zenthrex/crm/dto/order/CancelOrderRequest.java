package com.zenthrex.crm.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Cancel order request")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CancelOrderRequest {

    @Schema(description = "Cancellation reason")
    @NotNull(message = "Cancellation reason is required")
    private String reason;

    @Schema(description = "Additional notes")
    private String notes;

    @Schema(description = "Process refund")
    private Boolean processRefund = true;
}
