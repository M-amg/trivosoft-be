package com.zenthrex.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Pro upgrade status")
public record ProUpgradeStatusDto(
        @Schema(description = "Has pending request")
        Boolean hasPendingRequest,

        @Schema(description = "Current status")
        String currentStatus,

        @Schema(description = "Request ID")
        Long requestId,

        @Schema(description = "Submitted date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime submittedAt,

        @Schema(description = "Expected processing time in days")
        Integer expectedProcessingDays,

        @Schema(description = "Processing notes")
        String processingNotes
) {
}
