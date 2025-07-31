package com.zenthrex.notification.services;

import com.zenthrex.core.entites.user.User;
import org.springframework.stereotype.Service;

@Service
public class EmailTemplateServiceImpl implements EmailTemplateService {

    @Override
    public String buildWelcomeEmailText(User user, String temporaryPassword) {
        return String.format("""
                        Bonjour %s %s,
                        
                        Bienvenue dans l'équipe TrivoSoft!
                        
                        Votre compte a été créé avec succès. Voici vos informations de connexion:
                        
                        Email: %s
                        Mot de passe temporaire: %s
                        
                        IMPORTANT: Veuillez changer votre mot de passe lors de votre première connexion.
                        
                        Cordialement,
                        L'équipe TrivoSoft
                        """,
                user.getFirstname(), user.getLastname(), user.getEmail(), temporaryPassword);
    }

    @Override
    public String buildPasswordChangeText(User user) {
        return String.format("""
                        Bonjour %s %s,
                        
                        Votre mot de passe a été modifié avec succès.
                        
                        Si vous n'êtes pas à l'origine de cette modification, 
                        veuillez nous contacter immédiatement.
                        
                        Cordialement,
                        L'équipe TrivoSoft
                        """,
                user.getFirstname(), user.getLastname());
    }

    @Override
    public String buildStatusChangeText(User user, String newStatus, String reason) {
        return String.format("""
                        Bonjour %s %s,
                        
                        Le statut de votre compte a été modifié.
                        
                        Nouveau statut: %s
                        Raison: %s
                        
                        Pour toute question, n'hésitez pas à nous contacter.
                        
                        Cordialement,
                        L'équipe TrivoSoft
                        """,
                user.getFirstname(), user.getLastname(), newStatus,
                reason != null ? reason : "Non spécifiée");
    }

    @Override
    public String buildVerificationResultText(User user, boolean approved, String notes) {
        if (approved) {
            return String.format("""
                            Bonjour %s %s,
                            
                            Excellente nouvelle! Votre profil a été vérifié avec succès.
                            
                            Vous pouvez maintenant profiter de tous les avantages de votre compte vérifié.
                            
                            %s
                            
                            Cordialement,
                            L'équipe TrivoSoft
                            """,
                    user.getFirstname(), user.getLastname(),
                    notes != null ? "Notes: " + notes : "");
        } else {
            return String.format("""
                            Bonjour %s %s,
                            
                            Nous regrettons de vous informer que votre demande de vérification n'a pas pu être approuvée.
                            
                            Raison: %s
                            
                            Vous pouvez soumettre une nouvelle demande après avoir corrigé les points mentionnés.
                            
                            Cordialement,
                            L'équipe TrivoSoft
                            """,
                    user.getFirstname(), user.getLastname(),
                    notes != null ? notes : "Non spécifiée");
        }
    }

    @Override
    public String buildProUpgradeResultText(User user, boolean approved, String notes) {
        if (approved) {
            return String.format("""
                            Bonjour %s %s,
                            
                            Félicitations! Votre demande de passage en compte Pro a été approuvée.
                            
                            Vous pouvez maintenant:
                            - Publier des annonces
                            - Accéder aux statistiques avancées
                            - Bénéficier du support prioritaire
                            
                            %s
                            
                            Cordialement,
                            L'équipe TrivoSoft
                            """,
                    user.getFirstname(), user.getLastname(),
                    notes != null ? "Notes: " + notes : "");
        } else {
            return String.format("""
                            Bonjour %s %s,
                            
                            Nous regrettons de vous informer que votre demande de passage en compte Pro n'a pas pu être approuvée.
                            
                            Raison: %s
                            
                            Vous pouvez soumettre une nouvelle demande après avoir fourni les éléments manquants.
                            
                            Cordialement,
                            L'équipe TrivoSoft
                            """,
                    user.getFirstname(), user.getLastname(),
                    notes != null ? notes : "Non spécifiée");
        }
    }

    @Override
    public String buildSecurityAlertText(User user, String alertType) {
        return String.format("""
                        Bonjour %s %s,
                        
                        Nous avons détecté une activité suspecte sur votre compte.
                        
                        Type d'alerte: %s
                        Heure: %s
                        
                        Si ce n'était pas vous, veuillez:
                        1. Changer votre mot de passe immédiatement
                        2. Vérifier vos sessions actives
                        3. Nous contacter si nécessaire
                        
                        Cordialement,
                        L'équipe Sécurité TrivoSoft
                        """,
                user.getFirstname(), user.getLastname(), alertType,
                java.time.LocalDateTime.now().toString());
    }
}
