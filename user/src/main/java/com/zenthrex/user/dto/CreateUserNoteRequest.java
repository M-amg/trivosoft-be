package com.zenthrex.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Create user note request")
public record CreateUserNoteRequest(
        @Schema(description = "Note content")
        @NotBlank(message = "Note content is required")
        @Size(min = 10, max = 1000, message = "Note content must be between 10 and 1000 characters")
        String content,

        @Schema(description = "Note type")
        @NotBlank(message = "Note type is required")
        String type
) {
}
