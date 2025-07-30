package com.zenthrex.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Field error details")
public record FieldErrorDto(
        @Schema(description = "Field name")
        String field,

        @Schema(description = "Error message")
        String message,

        @Schema(description = "Rejected value")
        Object rejectedValue
) {
}
