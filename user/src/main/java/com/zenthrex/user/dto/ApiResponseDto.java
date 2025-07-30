package com.zenthrex.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "API response")
public record ApiResponseDto(
        @Schema(description = "Success status")
        Boolean success,

        @Schema(description = "Response message")
        String message,

        @Schema(description = "Additional data")
        Object data,

        @Schema(description = "Response timestamp")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime timestamp
) {
    public ApiResponseDto(String message) {
        this(true, message, null, LocalDateTime.now());
    }

    public ApiResponseDto(String message, Object data) {
        this(true, message, data, LocalDateTime.now());
    }
}
