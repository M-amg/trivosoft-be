// core/src/main/java/com/zenthrex/core/services/NotificationService.java
package com.zenthrex.core.services;

import com.zenthrex.core.entites.User;
import com.zenthrex.core.entites.accessory.Accessory;
import com.zenthrex.core.entites.caravan.Caravan;
import com.zenthrex.core.entites.caravan.CaravanBooking;
import com.zenthrex.core.entites.crm.ProcurementOrder;
import com.zenthrex.core.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final JavaMailSender mailSender;
    private final SMSService smsService;
    private final PushNotificationService pushNotificationService;

    // ==================== EMAIL NOTIFICATIONS ====================

    /**
     * Send order confirmation email
     */
    public void sendOrderConfirmation(ProcurementOrder order) {
        log.info("Sending order confirmation for order: {}", order.getNumber());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(getUserEmail(order));
            message.setSubject("Confirmation de commande - " + order.getNumber());
            message.setText(buildOrderConfirmationText(order));

            mailSender.send(message);
            log.info("Order confirmation email sent successfully");
        } catch (Exception e) {
            log.error("Failed to send order confirmation email", e);
        }
    }

    /**
     * Send order status update notification
     */
    public void sendOrderStatusUpdate(ProcurementOrder order, OrderStatus oldStatus) {
        log.info("Sending order status update for order: {} from {} to {}",
                order.getNumber(), oldStatus, order.getStatus());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(getUserEmail(order));
            message.setSubject("Mise à jour de commande - " + order.getNumber());
            message.setText(buildOrderStatusUpdateText(order, oldStatus));

            mailSender.send(message);

            // Also send push notification if enabled
            sendOrderStatusPushNotification(order, oldStatus);

        } catch (Exception e) {
            log.error("Failed to send order status update", e);
        }
    }

    /**
     * Send order completion notification
     */
    public void sendOrderCompletion(ProcurementOrder order) {
        log.info("Sending order completion notification for order: {}", order.getNumber());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(getUserEmail(order));
            message.setSubject("Commande livrée - " + order.getNumber());
            message.setText(buildOrderCompletionText(order));

            mailSender.send(message);
        } catch (Exception e) {
            log.error("Failed to send order completion notification", e);
        }
    }

    /**
     * Send order cancellation notification
     */
    public void sendOrderCancellation(ProcurementOrder order, String reason) {
        log.info("Sending order cancellation notification for order: {}", order.getNumber());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(getUserEmail(order));
            message.setSubject("Annulation de commande - " + order.getNumber());
            message.setText(buildOrderCancellationText(order, reason));

            mailSender.send(message);
        } catch (Exception e) {
            log.error("Failed to send order cancellation notification", e);
        }
    }

    // ==================== CARAVAN NOTIFICATIONS ====================

    /**
     * Send caravan booking confirmation
     */
    public void sendCaravanBookingConfirmation(CaravanBooking booking) {
        log.info("Sending caravan booking confirmation for booking: {}", booking.getId());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(getBookingUserEmail(booking));
            message.setSubject("Confirmation de réservation de caravane");
            message.setText(buildCaravanBookingConfirmationText(booking));

            mailSender.send(message);
        } catch (Exception e) {
            log.error("Failed to send caravan booking confirmation", e);
        }
    }

    /**
     * Send caravan approval notification to seller
     */
    public void sendCaravanApprovalNotification(Caravan caravan) {
        log.info("Sending caravan approval notification for caravan: {}", caravan.getId());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(caravan.getUser().getEmail());
            message.setSubject("Caravane approuvée - " + caravan.getTitle());
            message.setText(buildCaravanApprovalText(caravan));

            mailSender.send(message);
        } catch (Exception e) {
            log.error("Failed to send caravan approval notification", e);
        }
    }

    /**
     * Send caravan rejection notification to seller
     */
    public void sendCaravanRejectionNotification(Caravan caravan, String reason) {
        log.info("Sending caravan rejection notification for caravan: {}", caravan.getId());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(caravan.getUser().getEmail());
            message.setSubject("Caravane rejetée - " + caravan.getTitle());
            message.setText(buildCaravanRejectionText(caravan, reason));

            mailSender.send(message);
        } catch (Exception e) {
            log.error("Failed to send caravan rejection notification", e);
        }
    }

    // ==================== ACCESSORY NOTIFICATIONS ====================

    /**
     * Send accessory submission notification to admins
     */
    public void sendAccessorySubmissionNotification(Accessory accessory) {
        log.info("Sending accessory submission notification for accessory: {}", accessory.getId());

        try {
            // Send to admin users - you'd need to get admin emails from your user service
            String[] adminEmails = getAdminEmails();

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(adminEmails);
            message.setSubject("Nouvel accessoire à approuver - " + accessory.getTitle());
            message.setText(buildAccessorySubmissionText(accessory));

            mailSender.send(message);
        } catch (Exception e) {
            log.error("Failed to send accessory submission notification", e);
        }
    }

    /**
     * Send accessory approval notification to seller
     */
    public void sendAccessoryApprovalNotification(Accessory accessory, String notes) {
        log.info("Sending accessory approval notification for accessory: {}", accessory.getId());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(accessory.getSeller().getEmail());
            message.setSubject("Accessoire approuvé - " + accessory.getTitle());
            message.setText(buildAccessoryApprovalText(accessory, notes));

            mailSender.send(message);
        } catch (Exception e) {
            log.error("Failed to send accessory approval notification", e);
        }
    }

    /**
     * Send accessory rejection notification to seller
     */
    public void sendAccessoryRejectionNotification(Accessory accessory, String reason) {
        log.info("Sending accessory rejection notification for accessory: {}", accessory.getId());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(accessory.getSeller().getEmail());
            message.setSubject("Accessoire rejeté - " + accessory.getTitle());
            message.setText(buildAccessoryRejectionText(accessory, reason));

            mailSender.send(message);
        } catch (Exception e) {
            log.error("Failed to send accessory rejection notification", e);
        }
    }

    // ==================== USER NOTIFICATIONS ====================

    /**
     * Send agent welcome email with temporary password
     */
    public void sendAgentWelcomeEmail(User agent, String temporaryPassword) {
        log.info("Sending welcome email to new agent: {}", agent.getEmail());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(agent.getEmail());
            message.setSubject("Bienvenue dans l'équipe - Accès agent");
            message.setText(buildAgentWelcomeText(agent, temporaryPassword));

            mailSender.send(message);
        } catch (Exception e) {
            log.error("Failed to send agent welcome email", e);
        }
    }

    /**
     * Send password change confirmation
     */
    public void sendPasswordChangeConfirmation(User user) {
        log.info("Sending password change confirmation to: {}", user.getEmail());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("Mot de passe modifié");
            message.setText(buildPasswordChangeConfirmationText(user));

            mailSender.send(message);
        } catch (Exception e) {
            log.error("Failed to send password change confirmation", e);
        }
    }

    /**
     * Send user status change notification
     */
    public void sendStatusChangeNotification(User user, String newStatus, String reason) {
        log.info("Sending status change notification to: {}", user.getEmail());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("Statut de compte modifié");
            message.setText(buildStatusChangeText(user, newStatus, reason));

            mailSender.send(message);
        } catch (Exception e) {
            log.error("Failed to send status change notification", e);
        }
    }

    /**
     * Send profile verification submission confirmation
     */
    public void sendVerificationSubmissionConfirmation(User user) {
        log.info("Sending verification submission confirmation to: {}", user.getEmail());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("Demande de vérification reçue");
            message.setText(buildVerificationSubmissionText(user));

            mailSender.send(message);
        } catch (Exception e) {
            log.error("Failed to send verification submission confirmation", e);
        }
    }

    /**
     * Send pro upgrade request confirmation
     */
    public void sendProUpgradeRequestConfirmation(User user) {
        log.info("Sending pro upgrade request confirmation to: {}", user.getEmail());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("Demande de passage en compte Pro reçue");
            message.setText(buildProUpgradeRequestText(user));

            mailSender.send(message);
        } catch (Exception e) {
            log.error("Failed to send pro upgrade request confirmation", e);
        }
    }

    // ==================== PUSH NOTIFICATIONS ====================

    private void sendOrderStatusPushNotification(ProcurementOrder order, OrderStatus oldStatus) {
        try {
            String title = "Mise à jour de commande";
            String body = String.format("Votre commande %s est maintenant %s",
                    order.getNumber(), order.getStatus().getStatus());

            // Implementation would depend on your push notification service
            // pushNotificationService.sendToUser(getUserId(order), title, body);

        } catch (Exception e) {
            log.error("Failed to send push notification", e);
        }
    }

    // ==================== HELPER METHODS ====================

    private String getUserEmail(ProcurementOrder order) {
        // Implementation depends on how you link orders to users
        // This is a placeholder
        return "customer@example.com";
    }

    private String getBookingUserEmail(CaravanBooking booking) {
        // Implementation depends on how you link bookings to users
        // This is a placeholder
        return "customer@example.com";
    }

    private String[] getAdminEmails() {
        // Implementation would fetch admin emails from database
        // This is a placeholder
        return new String[]{"admin@trivosoft.com"};
    }

    // ==================== EMAIL CONTENT BUILDERS ====================

    private String buildOrderConfirmationText(ProcurementOrder order) {
        return String.format("""
                Bonjour,
                
                Nous avons bien reçu votre commande %s d'un montant de %.2f€.
                
                Votre commande est en cours de traitement et vous recevrez une confirmation 
                dès qu'elle sera validée.
                
                Détails de la commande:
                - Numéro: %s
                - Montant total: %.2f€
                - Statut: %s
                
                Merci pour votre confiance,
                L'équipe TrivoSoft
                """,
                order.getNumber(), order.getTotal(), order.getNumber(),
                order.getTotal(), order.getStatus().getStatus());
    }

    private String buildOrderStatusUpdateText(ProcurementOrder order, OrderStatus oldStatus) {
        return String.format("""
                Bonjour,
                
                Le statut de votre commande %s a été mis à jour.
                
                Ancien statut: %s
                Nouveau statut: %s
                
                Vous pouvez suivre l'évolution de votre commande dans votre espace client.
                
                Cordialement,
                L'équipe TrivoSoft
                """,
                order.getNumber(), oldStatus.getStatus(), order.getStatus().getStatus());
    }

    private String buildOrderCompletionText(ProcurementOrder order) {
        return String.format("""
                Bonjour,
                
                Votre commande %s a été livrée avec succès!
                
                Nous espérons que vous êtes satisfait(e) de votre achat.
                N'hésitez pas à laisser un avis sur les produits commandés.
                
                Merci pour votre confiance,
                L'équipe TrivoSoft
                """,
                order.getNumber());
    }

    private String buildOrderCancellationText(ProcurementOrder order, String reason) {
        return String.format("""
                Bonjour,
                
                Votre commande %s a été annulée.
                
                Raison: %s
                
                Si un remboursement est applicable, il sera traité dans les prochains jours ouvrés.
                
                Pour toute question, n'hésitez pas à nous contacter.
                
                Cordialement,
                L'équipe TrivoSoft
                """,
                order.getNumber(), reason != null ? reason : "Non spécifiée");
    }

    private String buildCaravanBookingConfirmationText(CaravanBooking booking) {
        return String.format("""
                Bonjour,
                
                Votre réservation de caravane a été confirmée!
                
                Détails de la réservation:
                - Du %s au %s
                - Lieu de récupération: %s
                - Montant: %.2f€
                
                Merci pour votre réservation,
                L'équipe TrivoSoft
                """,
                booking.getStartDate(), booking.getEndDate(),
                booking.getPickUp(), booking.getPriceUnite());
    }

    private String buildCaravanApprovalText(Caravan caravan) {
        return String.format("""
                Bonjour,
                
                Bonne nouvelle! Votre caravane "%s" a été approuvée et est maintenant 
                visible sur notre plateforme.
                
                Vous pouvez maintenant commencer à recevoir des réservations.
                
                Cordialement,
                L'équipe TrivoSoft
                """,
                caravan.getTitle());
    }

    private String buildCaravanRejectionText(Caravan caravan, String reason) {
        return String.format("""
                Bonjour,
                
                Nous regrettons de vous informer que votre caravane "%s" n'a pas pu être approuvée.
                
                Raison: %s
                
                Vous pouvez modifier votre annonce et la soumettre à nouveau pour validation.
                
                Cordialement,
                L'équipe TrivoSoft
                """,
                caravan.getTitle(), reason);
    }

    private String buildAccessorySubmissionText(Accessory accessory) {
        return String.format("""
                Nouvel accessoire soumis pour approbation:
                
                Titre: %s
                Vendeur: %s (%s)
                Prix: %.2f€
                Catégorie: %s
                
                Merci de vérifier et approuver/rejeter cet accessoire.
                """,
                accessory.getTitle(),
                accessory.getSeller().getFirstname() + " " + accessory.getSeller().getLastname(),
                accessory.getSeller().getEmail(),
                accessory.getPrice(),
                accessory.getCategory().getDisplayName());
    }

    private String buildAccessoryApprovalText(Accessory accessory, String notes) {
        return String.format("""
                Bonjour,
                
                Excellente nouvelle! Votre accessoire "%s" a été approuvé et est maintenant 
                disponible à la vente sur notre plateforme.
                
                %s
                
                Vous pouvez maintenant commencer à recevoir des commandes.
                
                Cordialement,
                L'équipe TrivoSoft
                """,
                accessory.getTitle(),
                notes != null ? "Notes: " + notes : "");
    }

    private String buildAccessoryRejectionText(Accessory accessory, String reason) {
        return String.format("""
                Bonjour,
                
                Nous regrettons de vous informer que votre accessoire "%s" n'a pas pu être approuvé.
                
                Raison: %s
                
                Vous pouvez modifier votre annonce selon les commentaires et la soumettre à nouveau.
                
                Cordialement,
                L'équipe TrivoSoft
                """,
                accessory.getTitle(), reason);
    }

    private String buildAgentWelcomeText(User agent, String temporaryPassword) {
        return String.format("""
                Bonjour %s,
                
                Bienvenue dans l'équipe TrivoSoft!
                
                Votre compte agent a été créé avec succès. Voici vos informations de connexion:
                
                Email: %s
                Mot de passe temporaire: %s
                
                IMPORTANT: Veuillez changer votre mot de passe lors de votre première connexion.
                
                Vous pouvez accéder à votre espace agent à l'adresse: [URL_ADMIN]
                
                Cordialement,
                L'équipe TrivoSoft
                """,
                agent.getFirstname(), agent.getEmail(), temporaryPassword);
    }

    private String buildPasswordChangeConfirmationText(User user) {
        return String.format("""
                Bonjour %s,
                
                Votre mot de passe a été modifié avec succès.
                
                Si vous n'êtes pas à l'origine de cette modification, 
                veuillez nous contacter immédiatement.
                
                Cordialement,
                L'équipe TrivoSoft
                """,
                user.getFirstname());
    }

    private String buildStatusChangeText(User user, String newStatus, String reason) {
        return String.format("""
                Bonjour %s,
                
                Le statut de votre compte a été modifié: %s
                
                %s
                
                Pour toute question, n'hésitez pas à nous contacter.
                
                Cordialement,
                L'équipe TrivoSoft
                """,
                user.getFirstname(), newStatus,
                reason != null ? "Raison: " + reason : "");
    }

    private String buildVerificationSubmissionText(User user) {
        return String.format("""
                Bonjour %s,
                
                Nous avons bien reçu votre demande de vérification de profil.
                
                Nos équipes vont examiner les documents fournis et vous 
                tiendront informé(e) du résultat dans les plus brefs délais.
                
                Délai de traitement habituel: 2-3 jours ouvrés.
                
                Cordialement,
                L'équipe TrivoSoft
                """,
                user.getFirstname());
    }

    private String buildProUpgradeRequestText(User user) {
        return String.format("""
                Bonjour %s,
                
                Nous avons bien reçu votre demande de passage en compte Pro.
                
                Nos équipes vont examiner votre dossier et vous tiendront 
                informé(e) du résultat dans les plus brefs délais.
                
                Délai de traitement habituel: 3-5 jours ouvrés.
                
                Cordialement,
                L'équipe TrivoSoft
                """,
                user.getFirstname());
    }
}
