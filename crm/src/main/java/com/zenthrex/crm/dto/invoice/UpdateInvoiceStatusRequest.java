package com.zenthrex.crm.dto.invoice;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Update invoice status request")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateInvoiceStatusRequest {

    @Schema(description = "New status")
    @NotNull(message = "Status is required")
    private String status;

    @Schema(description = "Status change notes")
    private String notes;
}
