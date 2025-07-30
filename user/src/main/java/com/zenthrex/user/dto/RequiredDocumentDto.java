package com.zenthrex.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Required document information")
public record RequiredDocumentDto(
        @Schema(description = "Document type")
        String type,

        @Schema(description = "Document name")
        String name,

        @Schema(description = "Is required")
        Boolean required,

        @Schema(description = "Description")
        String description,

        @Schema(description = "Is submitted")
        Boolean submitted
) {
}
