package com.zenthrex.notification.services;

import com.zenthrex.core.entites.accessory.Accessory;
import com.zenthrex.core.entites.caravan.Caravan;
import com.zenthrex.core.entites.caravan.CaravanBooking;
import com.zenthrex.core.entites.crm.Order;
import com.zenthrex.core.entites.user.User;

/**
 * Unified Notification Service Interface
 * Consolidates all notification functionality
 */
public interface NotificationService {

    // ==================== EMAIL NOTIFICATIONS ====================

    /**
     * User-related notifications
     */
    void sendWelcomeEmail(User user, String temporaryPassword);

    void sendPasswordChangeConfirmation(User user);

    void sendStatusChangeNotification(User user, String newStatus, String reason);

    void sendProfileVerificationResult(User user, boolean approved, String notes);

    void sendProUpgradeResult(User user, boolean approved, String notes);

    /**
     * Order-related notifications
     */
    void sendOrderConfirmation(Order order);

    void sendOrderStatusUpdate(Order order, String oldStatus);

    void sendOrderCompletion(Order order);

    void sendOrderCancellation(Order order, String reason);

    /**
     * Caravan-related notifications
     */
    void sendCaravanBookingConfirmation(CaravanBooking booking);

    void sendCaravanApprovalNotification(Caravan caravan);

    void sendCaravanRejectionNotification(Caravan caravan, String reason);

    /**
     * Accessory-related notifications
     */
    void sendAccessorySubmissionNotification(Accessory accessory);

    void sendAccessoryApprovalNotification(Accessory accessory, String notes);

    void sendAccessoryRejectionNotification(Accessory accessory, String reason);

    // ==================== IN-APP NOTIFICATIONS ====================

    /**
     * Create in-app notification
     */
    void createInAppNotification(User user, String title, String message, String type);

    void createInAppNotification(User user, String title, String message, String type, String actionUrl);

    /**
     * System notifications
     */
    void sendSystemMaintenanceNotification();

    void sendSecurityAlertNotification(User user, String alertType);

    // ==================== SMS NOTIFICATIONS ====================

    /**
     * SMS notifications for critical events
     */
    void sendBookingReminderSMS(User user, CaravanBooking booking);

    void sendSecurityCodeSMS(User user, String code);

    void sendPaymentConfirmationSMS(User user, Order order);

    // ==================== PUSH NOTIFICATIONS ====================

    /**
     * Push notifications for mobile apps
     */
    void sendPushNotification(User user, String title, String message);

    void sendBulkPushNotification(String userRole, String title, String message);

    // ==================== BULK NOTIFICATIONS ====================

    /**
     * Marketing and bulk communications
     */
    void sendMarketingEmail(String templateName, Object data);

    void sendNewsletterToSubscribers(String subject, String content);

    void sendBulkNotificationByRole(String role, String subject, String message);
}
