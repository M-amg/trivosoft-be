package com.zenthrex.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

@Schema(description = "Notification settings")
public record NotificationSettingsDto(
        @Schema(description = "Email notifications enabled")
        Boolean emailNotifications,

        @Schema(description = "SMS notifications enabled")
        Boolean smsNotifications,

        @Schema(description = "Push notifications enabled")
        Boolean pushNotifications,

        @Schema(description = "Marketing emails enabled")
        Boolean marketingEmails,

        @Schema(description = "Notification types preferences")
        Map<String, Boolean> typePreferences,

        @Schema(description = "Notification frequency")
        String frequency,

        @Schema(description = "Quiet hours start")
        String quietHoursStart,

        @Schema(description = "Quiet hours end")
        String quietHoursEnd
) {
}
