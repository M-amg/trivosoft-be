package com.zenthrex.user.service;

import com.zenthrex.core.entites.User;
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

    public void sendStatusChangeNotification(User user, String newStatus, String reason) {
        log.info("Sending status change notification to user: {}", user.getEmail());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("Changement de statut de votre compte");
            message.setText(String.format(
                    "Bonjour %s %s,\n\n" +
                            "Le statut de votre compte a été modifié.\n" +
                            "Nouveau statut: %s\n" +
                            "Raison: %s\n\n" +
                            "Si vous avez des questions, n'hésitez pas à nous contacter.\n\n" +
                            "Cordialement,\n" +
                            "L'équipe TrivoSoft",
                    user.getFirstname(), user.getLastname(), newStatus,
                    reason != null ? reason : "Non spécifiée"
            ));

            mailSender.send(message);
        } catch (Exception e) {
            log.error("Failed to send status change notification", e);
        }
    }

    public void sendAgentWelcomeEmail(User agent, String temporaryPassword) {
        log.info("Sending welcome email to new agent: {}", agent.getEmail());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(agent.getEmail());
            message.setSubject("Bienvenue dans l'équipe TrivoSoft");
            message.setText(String.format(
                    "Bonjour %s %s,\n\n" +
                            "Bienvenue dans l'équipe TrivoSoft!\n\n" +
                            "Votre compte agent a été créé avec succès.\n" +
                            "Email: %s\n" +
                            "Mot de passe temporaire: %s\n\n" +
                            "IMPORTANT: Veuillez changer votre mot de passe lors de votre première connexion.\n\n" +
                            "Cordialement,\n" +
                            "L'équipe TrivoSoft",
                    agent.getFirstname(), agent.getLastname(), agent.getEmail(), temporaryPassword
            ));

            mailSender.send(message);
        } catch (Exception e) {
            log.error("Failed to send agent welcome email", e);
        }
    }

    public void sendPasswordChangeConfirmation(User user) {
        log.info("Sending password change confirmation to: {}", user.getEmail());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("Mot de passe modifié");
            message.setText(String.format(
                    "Bonjour %s %s,\n\n" +
                            "Votre mot de passe a été modifié avec succès.\n\n" +
                            "Si vous n'êtes pas à l'origine de cette modification, " +
                            "veuillez nous contacter immédiatement.\n\n" +
                            "Cordialement,\n" +
                            "L'équipe TrivoSoft",
                    user.getFirstname(), user.getLastname()
            ));

            mailSender.send(message);
        } catch (Exception e) {
            log.error("Failed to send password change confirmation", e);
        }
    }

    public void sendVerificationSubmissionConfirmation(User user) {
        log.info("Sending verification submission confirmation to: {}", user.getEmail());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("Demande de vérification reçue");
            message.setText(String.format(
                    "Bonjour %s %s,\n\n" +
                            "Nous avons bien reçu votre demande de vérification de profil.\n\n" +
                            "Nos équipes vont examiner les documents fournis et vous " +
                            "tiendront informé(e) du résultat dans les plus brefs délais.\n\n" +
                            "Délai de traitement habituel: 2-3 jours ouvrés.\n\n" +
                            "Cordialement,\n" +
                            "L'équipe TrivoSoft",
                    user.getFirstname(), user.getLastname()
            ));

            mailSender.send(message);
        } catch (Exception e) {
            log.error("Failed to send verification submission confirmation", e);
        }
    }

    public void sendProUpgradeRequestConfirmation(User user) {
        log.info("Sending pro upgrade request confirmation to: {}", user.getEmail());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("Demande de passage en compte Pro reçue");
            message.setText(String.format(
                    "Bonjour %s %s,\n\n" +
                            "Nous avons bien reçu votre demande de passage en compte Pro.\n\n" +
                            "Nos équipes vont examiner votre dossier et vous tiendront " +
                            "informé(e) du résultat dans les plus brefs délais.\n\n" +
                            "Délai de traitement habituel: 3-5 jours ouvrés.\n\n" +
                            "Cordialement,\n" +
                            "L'équipe TrivoSoft",
                    user.getFirstname(), user.getLastname()
            ));

            mailSender.send(message);
        } catch (Exception e) {
            log.error("Failed to send pro upgrade request confirmation", e);
        }
    }
}