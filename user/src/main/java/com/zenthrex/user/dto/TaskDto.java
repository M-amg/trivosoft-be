package com.zenthrex.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Task information")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record TaskDto(
        @Schema(description = "Task ID")
        Long id,

        @Schema(description = "Task title")
        String title,

        @Schema(description = "Task description")
        String description,

        @Schema(description = "Task type")
        String type,

        @Schema(description = "Task status")
        String status,

        @Schema(description = "Task priority")
        String priority,

        @Schema(description = "Assigned to user")
        UserDto assignedTo,

        @Schema(description = "Related entity ID")
        Long relatedEntityId,

        @Schema(description = "Related entity type")
        String relatedEntityType,

        @Schema(description = "Due date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime dueDate,

        @Schema(description = "Created date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime createdAt,

        @Schema(description = "Updated date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime updatedAt
) {
}
