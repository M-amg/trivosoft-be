package com.zenthrex.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Document information")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record DocumentDto(
        @Schema(description = "Document ID")
        Long id,

        @Schema(description = "Document name")
        String name,

        @Schema(description = "Document type")
        String type,

        @Schema(description = "File URL")
        String fileUrl,

        @Schema(description = "File size in bytes")
        Long fileSize,

        @Schema(description = "Upload date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime uploadedAt
) {
}
