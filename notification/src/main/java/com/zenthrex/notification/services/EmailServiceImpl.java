// notification/src/main/java/com/zenthrex/notification/service/impl/EmailServiceImpl.java
package com.zenthrex.notification.services;

import com.zenthrex.core.entites.accessory.Accessory;
import com.zenthrex.core.entites.caravan.Caravan;
import com.zenthrex.core.entites.caravan.CaravanBooking;
import com.zenthrex.core.entites.crm.Order;
import com.zenthrex.core.entites.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final com.zenthrex.notification.services.EmailTemplateService templateService;

    @Override
    public void sendWelcomeEmail(User user, String temporaryPassword) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("Bienvenue dans l'équipe TrivoSoft");
            message.setText(templateService.buildWelcomeEmailText(user, temporaryPassword));

            mailSender.send(message);
            log.info("Welcome email sent successfully to: {}", user.getEmail());
        } catch (Exception e) {
            log.error("Failed to send welcome email to: {}", user.getEmail(), e);
        }
    }

    @Override
    public void sendPasswordChangeConfirmation(User user) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("Mot de passe modifié");
            message.setText(templateService.buildPasswordChangeText(user));

            mailSender.send(message);
            log.info("Password change confirmation sent to: {}", user.getEmail());
        } catch (Exception e) {
            log.error("Failed to send password change confirmation to: {}", user.getEmail(), e);
        }
    }

    @Override
    public void sendStatusChangeNotification(User user, String newStatus, String reason) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("Changement de statut de votre compte");
            message.setText(templateService.buildStatusChangeText(user, newStatus, reason));

            mailSender.send(message);
            log.info("Status change notification sent to: {}", user.getEmail());
        } catch (Exception e) {
            log.error("Failed to send status change notification to: {}", user.getEmail(), e);
        }
    }

    @Override
    public void sendProfileVerificationResult(User user, boolean approved, String notes) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject(approved ? "Profil vérifié" : "Vérification de profil rejetée");
            message.setText(templateService.buildVerificationResultText(user, approved, notes));

            mailSender.send(message);
            log.info("Verification result sent to: {} - approved: {}", user.getEmail(), approved);
        } catch (Exception e) {
            log.error("Failed to send verification result to: {}", user.getEmail(), e);
        }
    }

    @Override
    public void sendProUpgradeResult(User user, boolean approved, String notes) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject(approved ? "Demande Pro approuvée" : "Demande Pro rejetée");
            message.setText(templateService.buildProUpgradeResultText(user, approved, notes));

            mailSender.send(message);
            log.info("Pro upgrade result sent to: {} - approved: {}", user.getEmail(), approved);
        } catch (Exception e) {
            log.error("Failed to send pro upgrade result to: {}", user.getEmail(), e);
        }
    }

    @Override
    public void sendOrderConfirmation(Order order) {
        // Implementation for order confirmation
        log.info("Order confirmation email sent for order: {}", order.getOrderNumber());
    }

    @Override
    public void sendOrderStatusUpdate(Order order, String oldStatus) {
        // Implementation for order status update
        log.info("Order status update email sent for order: {}", order.getOrderNumber());
    }

    @Override
    public void sendOrderCompletion(Order order) {
        // Implementation for order completion
        log.info("Order completion email sent for order: {}", order.getOrderNumber());
    }

    @Override
    public void sendOrderCancellation(Order order, String reason) {
        // Implementation for order cancellation
        log.info("Order cancellation email sent for order: {}", order.getOrderNumber());
    }

    @Override
    public void sendCaravanBookingConfirmation(CaravanBooking booking) {
        // Implementation for caravan booking confirmation
        log.info("Caravan booking confirmation sent for booking: {}", booking.getId());
    }

    @Override
    public void sendCaravanApprovalNotification(Caravan caravan) {
        // Implementation for caravan approval
        log.info("Caravan approval notification sent for caravan: {}", caravan.getId());
    }

    @Override
    public void sendCaravanRejectionNotification(Caravan caravan, String reason) {
        // Implementation for caravan rejection
        log.info("Caravan rejection notification sent for caravan: {}", caravan.getId());
    }

    @Override
    public void sendAccessorySubmissionNotification(Accessory accessory) {
        // Implementation for accessory submission
        log.info("Accessory submission notification sent for accessory: {}", accessory.getId());
    }

    @Override
    public void sendAccessoryApprovalNotification(Accessory accessory, String notes) {
        // Implementation for accessory approval
        log.info("Accessory approval notification sent for accessory: {}", accessory.getId());
    }

    @Override
    public void sendAccessoryRejectionNotification(Accessory accessory, String reason) {
        // Implementation for accessory rejection
        log.info("Accessory rejection notification sent for accessory: {}", accessory.getId());
    }

    @Override
    public void sendSecurityAlert(User user, String alertType) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("Alerte de sécurité");
            message.setText(templateService.buildSecurityAlertText(user, alertType));

            mailSender.send(message);
            log.info("Security alert sent to: {}", user.getEmail());
        } catch (Exception e) {
            log.error("Failed to send security alert to: {}", user.getEmail(), e);
        }
    }

    @Override
    public void sendMarketingEmail(String templateName, Object data) {
        // Implementation for marketing emails
        log.info("Marketing email sent with template: {}", templateName);
    }

    @Override
    public void sendNewsletterToSubscribers(String subject, String content) {
        // Implementation for newsletter
        log.info("Newsletter sent to subscribers");
    }

    @Override
    public void sendBulkNotificationByRole(String role, String subject, String message) {
        // Implementation for bulk notifications
        log.info("Bulk notification sent to role: {}", role);
    }
}


