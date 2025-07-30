package com.zenthrex.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Avatar upload response")
public record AvatarUploadResponseDto(
        @Schema(description = "Avatar URL")
        String avatarUrl,

        @Schema(description = "Upload timestamp")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime uploadedAt
) {
}
