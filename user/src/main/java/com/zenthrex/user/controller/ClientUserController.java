package com.zenthrex.user.controller;

import com.zenthrex.user.api.ClientUserApi;
import com.zenthrex.user.dto.*;
import com.zenthrex.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
@PreAuthorize("hasAnyRole('BUYER', 'SELLER')")
public class ClientUserController implements ClientUserApi {

    private final UserService userService;

    @Override
    public ResponseEntity<UserProfileDto> getCurrentUserProfile() {
        log.info("Client fetching their profile");
        UserProfileDto profile = userService.getCurrentUserProfile();
        return ResponseEntity.ok(profile);
    }

    @Override
    public ResponseEntity<UserProfileDto> updateProfile(UpdateProfileRequest request) {
        log.info("Client updating their profile");
        UserProfileDto updatedProfile = userService.updateProfile(request);
        return ResponseEntity.ok(updatedProfile);
    }

    @Override
    public ResponseEntity<ApiResponseDto> changePassword(ChangePasswordRequest request) {
        log.info("Client changing password");
        userService.changePassword(request);
        return ResponseEntity.ok(new ApiResponseDto("Password changed successfully"));
    }

    @Override
    public ResponseEntity<AvatarUploadResponseDto> uploadAvatar(MultipartFile file) {
        log.info("Client uploading avatar - file size: {} bytes", file.getSize());
        AvatarUploadResponseDto response = userService.uploadAvatar(file);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseDto> deleteAvatar() {
        log.info("Client deleting avatar");
        userService.deleteAvatar();
        return ResponseEntity.ok(new ApiResponseDto("Avatar deleted successfully"));
    }

    @Override
    public ResponseEntity<ProfileVerificationStatusDto> getVerificationStatus() {
        log.info("Client checking verification status");
        ProfileVerificationStatusDto status = userService.getVerificationStatus();
        return ResponseEntity.ok(status);
    }

    @Override
    public ResponseEntity<ApiResponseDto> submitProfileVerification(SubmitVerificationRequest request) {
        log.info("Client submitting profile verification");
        userService.submitProfileVerification(request);
        return ResponseEntity.ok(new ApiResponseDto("Verification request submitted successfully"));
    }

    @Override
    @PreAuthorize("hasRole('BUYER')") // Only standard clients can request pro upgrade
    public ResponseEntity<ApiResponseDto> requestProUpgrade(ProUpgradeRequest request) {
        log.info("Client requesting pro upgrade");
        userService.requestProUpgrade(request);
        return ResponseEntity.ok(new ApiResponseDto("Pro upgrade request submitted successfully"));
    }

    @Override
    @PreAuthorize("hasRole('BUYER')")
    public ResponseEntity<ProUpgradeStatusDto> getProUpgradeStatus() {
        log.info("Client checking pro upgrade status");
        ProUpgradeStatusDto status = userService.getProUpgradeStatus();
        return ResponseEntity.ok(status);
    }

    @Override
    @PreAuthorize("hasRole('SELLER')") // Only pro clients can access dashboard stats
    public ResponseEntity<ClientDashboardStatsDto> getDashboardStats(String period) {
        log.info("Pro client fetching dashboard stats for period: {}", period);
        ClientDashboardStatsDto stats = userService.getDashboardStats(period);
        return ResponseEntity.ok(stats);
    }

    @Override
    public ResponseEntity<Page<NotificationDto>> getNotifications(String type, Boolean isRead, Pageable pageable) {
        log.info("Client fetching notifications - type: {}, isRead: {}", type, isRead);
        Page<NotificationDto> notifications = userService.getUserNotifications(type, isRead, pageable);
        return ResponseEntity.ok(notifications);
    }

    @Override
    public ResponseEntity<ApiResponseDto> markNotificationAsRead(Long id) {
        log.info("Client marking notification {} as read", id);
        userService.markNotificationAsRead(id);
        return ResponseEntity.ok(new ApiResponseDto("Notification marked as read"));
    }

    @Override
    public ResponseEntity<NotificationSettingsDto> getNotificationSettings() {
        log.info("Client fetching notification settings");
        NotificationSettingsDto settings = userService.getNotificationSettings();
        return ResponseEntity.ok(settings);
    }

    @Override
    public ResponseEntity<NotificationSettingsDto> updateNotificationSettings(UpdateNotificationSettingsRequest request) {
        log.info("Client updating notification settings");
        NotificationSettingsDto updatedSettings = userService.updateNotificationSettings(request);
        return ResponseEntity.ok(updatedSettings);
    }

    @Override
    public ResponseEntity<Page<UserActivityDto>> getAccountActivity(String activityType, String dateFrom,
                                                                    String dateTo, Pageable pageable) {
        log.info("Client fetching account activity");
        Page<UserActivityDto> activity = userService.getAccountActivity(activityType, dateFrom, dateTo, pageable);
        return ResponseEntity.ok(activity);
    }

    @Override
    public ResponseEntity<PrivacySettingsDto> updatePrivacySettings(UpdatePrivacySettingsRequest request) {
        log.info("Client updating privacy settings");
        PrivacySettingsDto updatedSettings = userService.updatePrivacySettings(request);
        return ResponseEntity.ok(updatedSettings);
    }

    @Override
    public ResponseEntity<PrivacySettingsDto> getPrivacySettings() {
        log.info("Client fetching privacy settings");
        PrivacySettingsDto settings = userService.getPrivacySettings();
        return ResponseEntity.ok(settings);
    }

    @Override
    public ResponseEntity<ApiResponseDto> requestAccountDeletion(DeleteAccountRequest request) {
        log.info("Client requesting account deletion");
        userService.requestAccountDeletion(request);
        return ResponseEntity.ok(new ApiResponseDto("Account deletion request submitted"));
    }

    @Override
    public ResponseEntity<byte[]> exportPersonalData(String format) {
        log.info("Client exporting personal data in format: {}", format);
        byte[] data = userService.exportPersonalData(format);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=my-data." + format.toLowerCase())
                .body(data);
    }

    @Override
    public ResponseEntity<TwoFactorStatusDto> getTwoFactorStatus() {
        log.info("Client checking 2FA status");
        TwoFactorStatusDto status = userService.getTwoFactorStatus();
        return ResponseEntity.ok(status);
    }

    @Override
    public ResponseEntity<TwoFactorSetupDto> enableTwoFactor() {
        log.info("Client enabling 2FA");
        TwoFactorSetupDto setup = userService.enableTwoFactor();
        return ResponseEntity.ok(setup);
    }

    @Override
    public ResponseEntity<ApiResponseDto> confirmTwoFactor(ConfirmTwoFactorRequest request) {
        log.info("Client confirming 2FA setup");
        userService.confirmTwoFactor(request);
        return ResponseEntity.ok(new ApiResponseDto("Two-factor authentication enabled successfully"));
    }

    @Override
    public ResponseEntity<ApiResponseDto> disableTwoFactor(DisableTwoFactorRequest request) {
        log.info("Client disabling 2FA");
        userService.disableTwoFactor(request);
        return ResponseEntity.ok(new ApiResponseDto("Two-factor authentication disabled successfully"));
    }

    @Override
    public ResponseEntity<BackupCodesDto> generateBackupCodes() {
        log.info("Client generating 2FA backup codes");
        BackupCodesDto backupCodes = userService.generateBackupCodes();
        return ResponseEntity.ok(backupCodes);
    }

    @Override
    public ResponseEntity<Page<UserSessionDto>> getConnectedSessions(Pageable pageable) {
        log.info("Client fetching connected sessions");
        Page<UserSessionDto> sessions = userService.getConnectedSessions(pageable);
        return ResponseEntity.ok(sessions);
    }

    @Override
    public ResponseEntity<ApiResponseDto> revokeSession(String sessionId) {
        log.info("Client revoking session: {}", sessionId);
        userService.revokeSession(sessionId);
        return ResponseEntity.ok(new ApiResponseDto("Session revoked successfully"));
    }

    @Override
    public ResponseEntity<ApiResponseDto> revokeAllSessions() {
        log.info("Client revoking all sessions");
        userService.revokeAllSessions();
        return ResponseEntity.ok(new ApiResponseDto("All sessions revoked successfully"));
    }
}