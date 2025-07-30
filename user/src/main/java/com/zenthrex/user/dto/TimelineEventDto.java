package com.zenthrex.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Map;

@Schema(description = "Timeline event")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record TimelineEventDto(
        @Schema(description = "Event ID")
        Long id,

        @Schema(description = "Event type")
        String eventType,

        @Schema(description = "Event description")
        String description,

        @Schema(description = "Actor user")
        UserDto actor,

        @Schema(description = "Event metadata")
        Map<String, Object> metadata,

        @Schema(description = "Event timestamp")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime timestamp
) {
}
