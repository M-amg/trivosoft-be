package com.zenthrex.crm.dto.invoice;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Send invoice request")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendInvoiceRequest {

    @Schema(description = "Send to email (optional, uses customer email if not provided)")
    private String email;

    @Schema(description = "Email subject")
    private String subject;

    @Schema(description = "Email message")
    private String message;

    @Schema(description = "Send copy to sender")
    private Boolean sendCopy = false;
}
