package com.zenthrex.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "Profile verification status")
public record ProfileVerificationStatusDto(
        @Schema(description = "Is profile verified")
        Boolean isVerified,

        @Schema(description = "Verification level")
        String verificationLevel,

        @Schema(description = "Verified date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime verifiedAt,

        @Schema(description = "Required documents")
        List<RequiredDocumentDto> requiredDocuments,

        @Schema(description = "Submitted documents")
        List<DocumentDto> submittedDocuments,

        @Schema(description = "Pending verification")
        Boolean hasPendingVerification,

        @Schema(description = "Verification notes")
        String verificationNotes
) {
}
