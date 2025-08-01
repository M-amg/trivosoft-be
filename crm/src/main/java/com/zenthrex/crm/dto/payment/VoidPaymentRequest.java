package com.zenthrex.crm.dto.payment;

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
