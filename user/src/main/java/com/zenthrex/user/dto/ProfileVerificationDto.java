package com.zenthrex.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "Profile verification details")
public record ProfileVerificationDto(
        @Schema(description = "Verification ID")
        Long id,

        @Schema(description = "User information")
        UserDto user,

        @Schema(description = "Verification type")
        String verificationType,

        @Schema(description = "Verification status")
        String status,

        @Schema(description = "Submitted documents")
        List<DocumentDto> documents,

        @Schema(description = "Verification notes")
        String notes,

        @Schema(description = "Submitted date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime submittedAt,

        @Schema(description = "Reviewed date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime reviewedAt,

        @Schema(description = "Reviewed by agent")
        Integer reviewedBy
) {
}
