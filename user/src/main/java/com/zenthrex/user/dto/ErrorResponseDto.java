package com.zenthrex.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "Error response")
public record ErrorResponseDto(
        @Schema(description = "Error status")
        Boolean success,

        @Schema(description = "Error message")
        String message,

        @Schema(description = "Error code")
        String errorCode,

        @Schema(description = "Field errors")
        List<FieldErrorDto> fieldErrors,

        @Schema(description = "Error timestamp")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime timestamp
) {
    public ErrorResponseDto(String message, String errorCode) {
        this(false, message, errorCode, null, LocalDateTime.now());
    }
}
