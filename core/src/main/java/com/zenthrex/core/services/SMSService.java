package com.zenthrex.core.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SMSService {

    /**
     * Send SMS notification
     */
    public void sendSMS(String phoneNumber, String message) {
        log.info("Sending SMS to: {}", phoneNumber);

        try {
            // Implementation would integrate with SMS provider (Twilio, AWS SNS, etc.)
            // This is a placeholder
            log.info("SMS sent successfully to: {}", phoneNumber);
        } catch (Exception e) {
            log.error("Failed to send SMS to: " + phoneNumber, e);
        }
    }

    /**
     * Send booking reminder SMS
     */
    public void sendBookingReminder(String phoneNumber, String bookingDetails) {
        String message = String.format(
                "Rappel: Votre réservation commence demain. Détails: %s",
                bookingDetails);
        sendSMS(phoneNumber, message);
    }

    /**
     * Send order status SMS
     */
    public void sendOrderStatusSMS(String phoneNumber, String orderNumber, String status) {
        String message = String.format(
                "Commande %s: Statut mis à jour - %s. Consultez votre compte pour plus de détails.",
                orderNumber, status);
        sendSMS(phoneNumber, message);
    }
}
