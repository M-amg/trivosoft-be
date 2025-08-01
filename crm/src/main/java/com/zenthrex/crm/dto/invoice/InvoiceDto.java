// ==================== INVOICE MANAGEMENT DTOs ====================

package com.zenthrex.crm.dto.invoice;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zenthrex.crm.dto.AddressDto;
import com.zenthrex.crm.dto.CustomerDto;
import com.zenthrex.crm.dto.payment.PaymentDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "Invoice information")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDto {

    @Schema(description = "Invoice ID")
    private Long id;

    @Schema(description = "Invoice number", example = "INV-2025-001234")
    private String invoiceNumber;

    @Schema(description = "Invoice type")
    private String invoiceType;

    @Schema(description = "Invoice status")
    private String status;

    @Schema(description = "Related order ID")
    private Long orderId;

    @Schema(description = "Customer information")
    @Valid
    private CustomerDto customer;

    @Schema(description = "Customer email")
    private String customerEmail;

    @Schema(description = "Issue date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate issueDate;

    @Schema(description = "Due date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    @Schema(description = "Subtotal amount")
    private BigDecimal subtotal;

    @Schema(description = "Tax amount")
    private BigDecimal taxAmount;

    @Schema(description = "Discount amount")
    private BigDecimal discountAmount;

    @Schema(description = "Total amount")
    private BigDecimal totalAmount;

    @Schema(description = "Paid amount")
    private BigDecimal paidAmount;

    @Schema(description = "Balance amount")
    private BigDecimal balanceAmount;

    @Schema(description = "Currency")
    private String currency;

    @Schema(description = "Payment terms")
    private String paymentTerms;

    @Schema(description = "Invoice notes")
    private String notes;

    @Schema(description = "Internal notes")
    private String internalNotes;

    @Schema(description = "Billing address")
    @Valid
    private AddressDto billingAddress;

    @Schema(description = "PDF path")
    private String pdfPath;

    @Schema(description = "PDF download URL")
    private String pdfUrl;

    @Schema(description = "Sent date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime sentAt;

    @Schema(description = "Viewed date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime viewedAt;

    @Schema(description = "Invoice items")
    @Valid
    private List<@Valid InvoiceItemDto> invoiceItems;

    @Schema(description = "Payment records")
    @Valid
    private List<@Valid PaymentDto> payments;

    @Schema(description = "Created date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @Schema(description = "Updated date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;

    @Schema(description = "Created by user ID")
    private Long createdBy;
}

