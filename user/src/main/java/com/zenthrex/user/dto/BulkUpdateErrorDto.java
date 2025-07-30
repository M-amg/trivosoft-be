package com.zenthrex.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Bulk update error")
public record BulkUpdateErrorDto(
        @Schema(description = "User ID")
        Long userId,

        @Schema(description = "Error message")
        String errorMessage
) {
}
