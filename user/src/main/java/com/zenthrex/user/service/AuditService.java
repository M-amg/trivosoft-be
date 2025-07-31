package com.zenthrex.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuditService {

    // TODO: In production, these would be saved to an audit log table

    public void logUserStatusChange(Long userId, String oldStatus, String newStatus, String reason) {
        log.info("AUDIT: User {} status changed from {} to {}. Reason: {}. Time: {}",
                userId, oldStatus, newStatus, reason, LocalDateTime.now());
    }

    public void logUserRoleChange(Long userId, String oldRole, String newRole, String reason) {
        log.info("AUDIT: User {} role changed from {} to {}. Reason: {}. Time: {}",
                userId, oldRole, newRole, reason, LocalDateTime.now());
    }

    public void logUserDeletion(Long userId, Integer deletedBy) {
        log.info("AUDIT: User {} deleted by user {}. Time: {}",
                userId, deletedBy, LocalDateTime.now());
    }

    public void logProfileUpdate(Integer userId) {
        log.info("AUDIT: User {} updated their profile. Time: {}",
                userId, LocalDateTime.now());
    }

    public void logPasswordChange(Integer userId) {
        log.info("AUDIT: User {} changed their password. Time: {}",
                userId, LocalDateTime.now());
    }

    public void logVerificationSubmission(Integer userId, String verificationType) {
        log.info("AUDIT: User {} submitted verification request. Type: {}. Time: {}",
                userId, verificationType, LocalDateTime.now());
    }

    public void logProUpgradeRequest(Integer userId) {
        log.info("AUDIT: User {} requested Pro upgrade. Time: {}",
                userId, LocalDateTime.now());
    }

    public void logLoginAttempt(String email, boolean success, String ipAddress) {
        log.info("AUDIT: Login attempt for {} from IP {}. Success: {}. Time: {}",
                email, ipAddress, success, LocalDateTime.now());
    }

    public void logLogout(Integer userId) {
        log.info("AUDIT: User {} logged out. Time: {}",
                userId, LocalDateTime.now());
    }
}