package com.zenthrex.notification.services;

import com.zenthrex.core.entites.user.User;

public interface EmailTemplateService {

    String buildWelcomeEmailText(User user, String temporaryPassword);

    String buildPasswordChangeText(User user);

    String buildStatusChangeText(User user, String newStatus, String reason);

    String buildVerificationResultText(User user, boolean approved, String notes);

    String buildProUpgradeResultText(User user, boolean approved, String notes);

    String buildSecurityAlertText(User user, String alertType);
}
