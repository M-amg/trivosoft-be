package com.zenthrex.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Map;

@Schema(description = "User notification")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record NotificationDto(
        @Schema(description = "Notification ID")
        Long id,

        @Schema(description = "Notification title")
        String title,

        @Schema(description = "Notification message")
        String message,

        @Schema(description = "Notification type")
        String type,

        @Schema(description = "Priority level")
        String priority,

        @Schema(description = "Is read")
        Boolean isRead,

        @Schema(description = "Action URL")
        String actionUrl,

        @Schema(description = "Metadata")
        Map<String, Object> metadata,

        @Schema(description = "Created date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime createdAt,

        @Schema(description = "Read date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime readAt
) {
}
