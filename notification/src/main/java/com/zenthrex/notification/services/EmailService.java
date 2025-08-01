// notification/src/main/java/com/zenthrex/notification/service/EmailService.java
package com.zenthrex.notification.services;

import com.zenthrex.core.entites.accessory.Accessory;
import com.zenthrex.core.entites.caravan.Caravan;
import com.zenthrex.core.entites.caravan.CaravanBooking;
import com.zenthrex.core.entites.crm.Order;

import com.zenthrex.core.entites.user.User;

public interface EmailService {

    // User notifications
    void sendWelcomeEmail(User user, String temporaryPassword);
    void sendPasswordChangeConfirmation(User user);
    void sendStatusChangeNotification(User user, String newStatus, String reason);
    void sendProfileVerificationResult(User user, boolean approved, String notes);
    void sendProUpgradeResult(User user, boolean approved, String notes);

    // Order notifications
    void sendOrderConfirmation(Order order);
    void sendOrderStatusUpdate(Order order, String oldStatus);
    void sendOrderCompletion(Order order);
    void sendOrderCancellation(Order order, String reason);

    // Caravan notifications
    void sendCaravanBookingConfirmation(CaravanBooking booking);
    void sendCaravanApprovalNotification(Caravan caravan);
    void sendCaravanRejectionNotification(Caravan caravan, String reason);

    // Accessory notifications
    void sendAccessorySubmissionNotification(Accessory accessory);
    void sendAccessoryApprovalNotification(Accessory accessory, String notes);
    void sendAccessoryRejectionNotification(Accessory accessory, String reason);

    // Security notifications
    void sendSecurityAlert(User user, String alertType);

    // Marketing notifications
    void sendMarketingEmail(String templateName, Object data);
    void sendNewsletterToSubscribers(String subject, String content);
    void sendBulkNotificationByRole(String role, String subject, String message);
}
