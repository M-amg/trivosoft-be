package com.zenthrex.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "Detailed pro upgrade request")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProUpgradeRequestDetailDto(
        @Schema(description = "Request ID")
        Long id,

        @Schema(description = "User details")
        ClientDetailDto user,

        @Schema(description = "Business information")
        BusinessInfoDto businessInfo,

        @Schema(description = "Request status")
        String status,

        @Schema(description = "Processing notes")
        String processingNotes,

        @Schema(description = "Uploaded documents")
        List<DocumentDto> documents,

        @Schema(description = "Request timeline")
        List<TimelineEventDto> timeline,

        @Schema(description = "Request date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime createdAt,

        @Schema(description = "Processing date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime processedAt,

        @Schema(description = "Processed by agent")
        UserDto processedBy
) {
}
