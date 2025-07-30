package com.zenthrex.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;

import java.util.Map;

@Schema(description = "Update notification settings request")
public record UpdateNotificationSettingsRequest(
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

        @Schema(description = "Quiet hours start (HH:mm format)")
        @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Invalid time format")
        String quietHoursStart,

        @Schema(description = "Quiet hours end (HH:mm format)")
        @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Invalid time format")
        String quietHoursEnd
) {
}
