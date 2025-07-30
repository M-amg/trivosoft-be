package com.zenthrex.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * Advanced User DTOs for User Module
 * Complex DTOs for statistics, notifications, and advanced features
 */

// ==================== PRO UPGRADE DTOs ====================

@Schema(description = "Pro upgrade request details")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProUpgradeRequestDto(
        @Schema(description = "Request ID")
        Long id,

        @Schema(description = "User information")
        UserDto user,

        @Schema(description = "Business name")
        String businessName,

        @Schema(description = "Business type")
        String businessType,

        @Schema(description = "Tax ID")
        String taxId,

        @Schema(description = "Business address")
        String businessAddress,

        @Schema(description = "Business phone")
        String businessPhone,

        @Schema(description = "Website URL")
        String website,

        @Schema(description = "Years of experience")
        Integer yearsOfExperience,

        @Schema(description = "Upgrade reason")
        String reason,

        @Schema(description = "Request status")
        String status,

        @Schema(description = "Processing notes")
        String processingNotes,

        @Schema(description = "Request date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime createdAt,

        @Schema(description = "Processing date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime processedAt,

        @Schema(description = "Processed by user ID")
        Long processedBy
) {
}
