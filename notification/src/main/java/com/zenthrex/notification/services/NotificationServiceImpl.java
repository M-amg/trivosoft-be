package com.zenthrex.notification.services;

import com.zenthrex.core.entites.accessory.Accessory;
import com.zenthrex.core.entites.caravan.Caravan;
import com.zenthrex.core.entites.caravan.CaravanBooking;
import com.zenthrex.core.entites.crm.ProcurementOrder;
import com.zenthrex.core.entites.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final EmailService emailService;
    private final InAppNotificationService inAppNotificationService;
    private final SMSService smsService;
    private final PushNotificationService pushNotificationService;

    // ==================== EMAIL NOTIFICATIONS ====================

    @Override
    public void sendWelcomeEmail(User user, String temporaryPassword) {
        log.info("Sending welcome email to: {}", user.getEmail());
        emailService.sendWelcomeEmail(user, temporaryPassword);
    }

    @Override
    public void sendPasswordChangeConfirmation(User user) {
        log.info("Sending password change confirmation to: {}", user.getEmail());
        emailService.sendPasswordChangeConfirmation(user);
    }

    @Override
    public void sendStatusChangeNotification(User user, String newStatus, String reason) {
        log.info("Sending status change notification to: {}", user.getEmail());
        emailService.sendStatusChangeNotification(user, newStatus, reason);
        inAppNotificationService.createNotification(user, "Statut du compte modifié",
                "Votre statut de compte a été modifié: " + newStatus, "ACCOUNT");
    }

    @Override
    public void sendProfileVerificationResult(User user, boolean approved, String notes) {
        log.info("Sending verification result to: {} - approved: {}", user.getEmail(), approved);
        emailService.sendProfileVerificationResult(user, approved, notes);

        String message = approved ?
                "Votre profil a été vérifié avec succès" :
                "Votre demande de vérification a été rejetée";
        inAppNotificationService.createNotification(user, "Résultat de vérification", message, "VERIFICATION");
    }

    @Override
    public void sendProUpgradeResult(User user, boolean approved, String notes) {
        log.info("Sending pro upgrade result to: {} - approved: {}", user.getEmail(), approved);
        emailService.sendProUpgradeResult(user, approved, notes);

        String message = approved ?
                "Votre demande de passage Pro a été approuvée" :
                "Votre demande de passage Pro a été rejetée";
        inAppNotificationService.createNotification(user, "Demande Pro", message, "UPGRADE");
    }

    // ==================== ORDER NOTIFICATIONS ====================

    @Override
    public void sendOrderConfirmation(ProcurementOrder order) {
        log.info("Sending order confirmation for: {}", order.getNumber());
        emailService.sendOrderConfirmation(order);

        // Create in-app notification
        User customer = getOrderCustomer(order); // Helper method to get customer
        if (customer != null) {
            inAppNotificationService.createNotification(customer,
                    "Commande confirmée",
                    "Votre commande " + order.getNumber() + " a été confirmée",
                    "ORDER");
        }
    }

    @Override
    public void sendOrderStatusUpdate(ProcurementOrder order, String oldStatus) {
        log.info("Sending order status update for: {}", order.getNumber());
        emailService.sendOrderStatusUpdate(order, oldStatus);
    }

    @Override
    public void sendOrderCompletion(ProcurementOrder order) {
        log.info("Sending order completion for: {}", order.getNumber());
        emailService.sendOrderCompletion(order);
    }

    @Override
    public void sendOrderCancellation(ProcurementOrder order, String reason) {
        log.info("Sending order cancellation for: {}", order.getNumber());
        emailService.sendOrderCancellation(order, reason);
    }

    // ==================== CARAVAN NOTIFICATIONS ====================

    @Override
    public void sendCaravanBookingConfirmation(CaravanBooking booking) {
        log.info("Sending caravan booking confirmation for: {}", booking.getId());
        emailService.sendCaravanBookingConfirmation(booking);

        // Send SMS reminder
        User customer = getBookingCustomer(booking); // Helper method
        if (customer != null && customer.getPhone() != null) {
            smsService.sendBookingConfirmation(customer.getPhone(), booking);
        }
    }

    @Override
    public void sendCaravanApprovalNotification(Caravan caravan) {
        log.info("Sending caravan approval for: {}", caravan.getId());
        emailService.sendCaravanApprovalNotification(caravan);

        if (caravan.getUser() != null) {
            inAppNotificationService.createNotification(caravan.getUser(),
                    "Caravane approuvée",
                    "Votre caravane '" + caravan.getTitle() + "' a été approuvée",
                    "APPROVAL");
        }
    }

    @Override
    public void sendCaravanRejectionNotification(Caravan caravan, String reason) {
        log.info("Sending caravan rejection for: {}", caravan.getId());
        emailService.sendCaravanRejectionNotification(caravan, reason);
    }

    // ==================== ACCESSORY NOTIFICATIONS ====================

    @Override
    public void sendAccessorySubmissionNotification(Accessory accessory) {
        log.info("Sending accessory submission notification for: {}", accessory.getId());
        emailService.sendAccessorySubmissionNotification(accessory);
    }

    @Override
    public void sendAccessoryApprovalNotification(Accessory accessory, String notes) {
        log.info("Sending accessory approval for: {}", accessory.getId());
        emailService.sendAccessoryApprovalNotification(accessory, notes);
    }

    @Override
    public void sendAccessoryRejectionNotification(Accessory accessory, String reason) {
        log.info("Sending accessory rejection for: {}", accessory.getId());
        emailService.sendAccessoryRejectionNotification(accessory, reason);
    }

    // ==================== IN-APP NOTIFICATIONS ====================

    @Override
    public void createInAppNotification(User user, String title, String message, String type) {
        inAppNotificationService.createNotification(user, title, message, type);
    }

    @Override
    public void createInAppNotification(User user, String title, String message, String type, String actionUrl) {
        inAppNotificationService.createNotification(user, title, message, type, actionUrl);
    }

    @Override
    public void sendSystemMaintenanceNotification() {
        log.info("Sending system maintenance notification to all users");
        // Implementation for system-wide notifications
    }

    @Override
    public void sendSecurityAlertNotification(User user, String alertType) {
        log.info("Sending security alert to: {}", user.getEmail());
        emailService.sendSecurityAlert(user, alertType);
        inAppNotificationService.createNotification(user, "Alerte de sécurité",
                "Activité suspecte détectée sur votre compte", "SECURITY");
    }

    // ==================== SMS NOTIFICATIONS ====================

    @Override
    public void sendBookingReminderSMS(User user, CaravanBooking booking) {
        if (user.getPhone() != null) {
            smsService.sendBookingReminder(user.getPhone(), booking);
        }
    }

    @Override
    public void sendSecurityCodeSMS(User user, String code) {
        if (user.getPhone() != null) {
            smsService.sendSecurityCode(user.getPhone(), code);
        }
    }

    @Override
    public void sendPaymentConfirmationSMS(User user, ProcurementOrder order) {
        if (user.getPhone() != null) {
            smsService.sendPaymentConfirmation(user.getPhone(), order);
        }
    }

    // ==================== PUSH NOTIFICATIONS ====================

    @Override
    public void sendPushNotification(User user, String title, String message) {
        pushNotificationService.sendToUser(user.getId(), title, message);
    }

    @Override
    public void sendBulkPushNotification(String userRole, String title, String message) {
        pushNotificationService.sendToRole(userRole, title, message);
    }

    // ==================== BULK NOTIFICATIONS ====================

    @Override
    public void sendMarketingEmail(String templateName, Object data) {
        log.info("Sending marketing email with template: {}", templateName);
        emailService.sendMarketingEmail(templateName, data);
    }

    @Override
    public void sendNewsletterToSubscribers(String subject, String content) {
        log.info("Sending newsletter to subscribers");
        emailService.sendNewsletterToSubscribers(subject, content);
    }

    @Override
    public void sendBulkNotificationByRole(String role, String subject, String message) {
        log.info("Sending bulk notification to role: {}", role);
        emailService.sendBulkNotificationByRole(role, subject, message);
    }

    // ==================== HELPER METHODS ====================

    private User getOrderCustomer(ProcurementOrder order) {
        // Implementation would get customer from order
        // This is a placeholder
        return null;
    }

    private User getBookingCustomer(CaravanBooking booking) {
        // Implementation would get customer from booking
        // This is a placeholder
        return null;
    }
}
