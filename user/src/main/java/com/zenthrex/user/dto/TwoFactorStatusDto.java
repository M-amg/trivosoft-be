package com.zenthrex.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Two-factor authentication status")
public record TwoFactorStatusDto(
        @Schema(description = "Is 2FA enabled")
        Boolean enabled,

        @Schema(description = "Setup date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime enabledAt,

        @Schema(description = "Backup codes count")
        Integer backupCodesCount,

        @Schema(description = "Last used date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime lastUsedAt
) {
}
