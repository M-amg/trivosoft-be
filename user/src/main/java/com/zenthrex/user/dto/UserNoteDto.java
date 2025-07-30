package com.zenthrex.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "User note")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserNoteDto(
        @Schema(description = "Note ID")
        Long id,

        @Schema(description = "Note content")
        String content,

        @Schema(description = "Note type")
        String type,

        @Schema(description = "Created by agent")
        UserDto createdBy,

        @Schema(description = "Creation date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime createdAt,

        @Schema(description = "Last update date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime updatedAt
) {
}
