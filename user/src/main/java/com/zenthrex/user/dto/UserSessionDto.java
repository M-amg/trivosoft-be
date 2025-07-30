package com.zenthrex.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "User session information")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserSessionDto(
        @Schema(description = "Session ID")
        String sessionId,

        @Schema(description = "Device information")
        String deviceInfo,

        @Schema(description = "IP address")
        String ipAddress,

        @Schema(description = "Location")
        String location,

        @Schema(description = "Is current session")
        Boolean isCurrent,

        @Schema(description = "Login date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime loginAt,

        @Schema(description = "Last activity date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime lastActivityAt,

        @Schema(description = "Expires at")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime expiresAt
) {
}
