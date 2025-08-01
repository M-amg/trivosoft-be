package com.zenthrex.crm.dto.invoice;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Schema(description = "Create invoice request")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvoiceRequest {

    @Schema(description = "Invoice type")
    @NotNull(message = "Invoice type is required")
    private String invoiceType;

    @Schema(description = "Order ID (optional)")
    private Long orderId;

    @Schema(description = "Customer ID")
    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @Schema(description = "Issue date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate issueDate;

    @Schema(description = "Due date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    @Schema(description = "Invoice items")
    @Valid
    @NotNull(message = "Invoice items are required")
    private List<@Valid CreateInvoiceItemRequest> items;

    @Schema(description = "Payment terms")
    private String paymentTerms = "Net 30";

    @Schema(description = "Invoice notes")
    private String notes;

    @Schema(description = "Internal notes")
    private String internalNotes;

    @Schema(description = "Currency")
    private String currency = "EUR";

    @Schema(description = "Template code")
    private String templateCode = "DEFAULT";
}
