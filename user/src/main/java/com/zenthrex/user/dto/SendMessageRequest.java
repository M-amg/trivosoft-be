package com.zenthrex.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Schema(description = "Send message request")
public record SendMessageRequest(
        @Schema(description = "Message content")
        @NotBlank(message = "Message content is required")
        @Size(min = 1, max = 1000, message = "Message must be between 1 and 1000 characters")
        String content,

        @Schema(description = "Message type")
        String messageType,

        @Schema(description = "Attachments")
        List<Long> attachmentIds
) {
}
