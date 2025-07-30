package com.zenthrex.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Two-factor authentication setup")
public record TwoFactorSetupDto(
        @Schema(description = "QR code URL")
        String qrCodeUrl,

        @Schema(description = "Secret key")
        String secretKey,

        @Schema(description = "Backup codes")
        List<String> backupCodes,

        @Schema(description = "Setup instructions")
        String instructions
) {
}
