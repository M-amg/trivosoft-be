package com.zenthrex.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Map;

@Schema(description = "User activity record")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserActivityDto(
        @Schema(description = "Activity ID")
        Long id,

        @Schema(description = "Activity type")
        String activityType,

        @Schema(description = "Activity description")
        String description,

        @Schema(description = "IP address")
        String ipAddress,

        @Schema(description = "User agent")
        String userAgent,

        @Schema(description = "Activity metadata")
        Map<String, Object> metadata,

        @Schema(description = "Activity timestamp")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime timestamp
) {
}
