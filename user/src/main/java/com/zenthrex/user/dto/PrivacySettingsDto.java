package com.zenthrex.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Privacy settings")
public record PrivacySettingsDto(
        @Schema(description = "Profile visibility")
        String profileVisibility,

        @Schema(description = "Show email to other users")
        Boolean showEmail,

        @Schema(description = "Show phone to other users")
        Boolean showPhone,

        @Schema(description = "Allow marketing communications")
        Boolean allowMarketing,

        @Schema(description = "Allow analytics tracking")
        Boolean allowAnalytics,

        @Schema(description = "Data retention preference")
        String dataRetention,

        @Schema(description = "Third-party data sharing")
        Boolean thirdPartySharing
) {
}
