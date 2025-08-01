package com.zenthrex.notification.services;

import com.zenthrex.core.entites.caravan.CaravanBooking;
import com.zenthrex.core.entites.crm.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SMSServiceImpl implements SMSService {

    @Override
    public void sendBookingConfirmation(String phoneNumber, CaravanBooking booking) {
        String message = String.format(
                "TrivoSoft: Votre réservation est confirmée! Du %s au %s. Référence: %s",
                booking.getStartDate(), booking.getEndDate(), booking.getId());

        sendSMS(phoneNumber, message);
        log.info("Booking confirmation SMS sent to: {}", phoneNumber);
    }

    @Override
    public void sendBookingReminder(String phoneNumber, CaravanBooking booking) {
        String message = String.format(
                "TrivoSoft: Rappel - Votre réservation commence demain (%s). Bon voyage!",
                booking.getStartDate());

        sendSMS(phoneNumber, message);
        log.info("Booking reminder SMS sent to: {}", phoneNumber);
    }

    @Override
    public void sendSecurityCode(String phoneNumber, String code) {
        String message = String.format(
                "TrivoSoft: Votre code de sécurité est: %s. Ne le partagez avec personne.",
                code);

        sendSMS(phoneNumber, message);
        log.info("Security code SMS sent to: {}", phoneNumber);
    }

    @Override
    public void sendPaymentConfirmation(String phoneNumber, Order order) {
        String message = String.format(
                "TrivoSoft: Paiement confirmé pour la commande %s (%.2f€). Merci!",
                order.getOrderNumber(), order.getTotalAmount());

        sendSMS(phoneNumber, message);
        log.info("Payment confirmation SMS sent to: {}", phoneNumber);
    }

    private void sendSMS(String phoneNumber, String message) {
        try {
            // Integration with SMS provider (Twilio, AWS SNS, etc.)
            // This is a placeholder implementation
            log.info("SMS sent to {}: {}", phoneNumber, message);
        } catch (Exception e) {
            log.error("Failed to send SMS to: {}", phoneNumber, e);
        }
    }
}
