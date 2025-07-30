package com.zenthrex.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "Backup codes")
public record BackupCodesDto(
        @Schema(description = "Generated backup codes")
        List<String> codes,

        @Schema(description = "Generation date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime generatedAt,

        @Schema(description = "Usage instructions")
        String instructions
) {
}
