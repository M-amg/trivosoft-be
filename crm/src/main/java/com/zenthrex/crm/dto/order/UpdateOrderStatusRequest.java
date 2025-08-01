package com.zenthrex.crm.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Update order status request")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderStatusRequest {

    @Schema(description = "New status")
    @NotNull(message = "Status is required")
    private String status;

    @Schema(description = "Status change reason")
    private String reason;

    @Schema(description = "Additional notes")
    private String notes;
}
