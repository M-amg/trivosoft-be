package com.zenthrex.user.service;

import com.zenthrex.user.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * User Management Service Interface
 * Defines all user-related business operations following SOA architecture
 * Module: user-service
 */
public interface UserService {

    // ==================== ADMIN OPERATIONS ====================

    /**
     * Get all users with filtering and pagination (Admin only)
     */
    Page<UserDto> getAllUsers(String role, String status, String search, Pageable pageable);

    /**
     * Get user by ID with detailed information (Admin only)
     */
    UserDetailDto getUserById(Long id);

    /**
     * Update user status with reason (Admin only)
     */
    void updateUserStatus(Long id, UpdateUserStatusRequest request);

    /**
     * Update user role (Admin only)
     */
    void updateUserRole(Long id, UpdateUserRoleRequest request);

    /**
     * Soft delete user account (Admin only)
     */
    void deleteUser(Long id);

    /**
     * Create new agent account (Admin only)
     */
    UserDto createAgent(CreateAgentRequest request);

    /**
     * Search users with advanced criteria (Admin only)
     */
    Page<UserDto> searchUsers(String query, String role, String status,
                              String registeredFrom, String registeredTo, Pageable pageable);

    /**
     * Get comprehensive user statistics (Admin only)
     */
    UserStatisticsDto getUserStatistics(String period);

    /**
     * Export users data to file (Admin only)
     */
    byte[] exportUsers(String format, String role, String status);

    /**
     * Bulk update multiple users (Admin only)
     */
    BulkUpdateResultDto bulkUpdateUsers(BulkUpdateUsersRequest request);

    // ==================== AGENT OPERATIONS ====================

    /**
     * Get all clients with filtering (Agent only)
     */
    Page<UserDto> getAllClients(String clientType, String status, String search,
                                Boolean verified, Pageable pageable);

    /**
     * Get detailed client information (Agent only)
     */
    ClientDetailDto getClientDetails(Long id);

    /**
     * Verify or reject client profile (Agent only)
     */
    void verifyClientProfile(Long id, VerifyProfileRequest request);

    /**
     * Get pending profile verification requests (Agent only)
     */
    Page<ProfileVerificationDto> getPendingVerifications(Pageable pageable);

    /**
     * Get pro upgrade requests (Agent only)
     */
    Page<ProUpgradeRequestDto> getProUpgradeRequests(String status, Pageable pageable);

    /**
     * Get detailed pro upgrade request (Agent only)
     */
    ProUpgradeRequestDetailDto getProUpgradeRequestDetails(Long id);

    /**
     * Approve pro upgrade request (Agent only)
     */
    void approveProUpgrade(Long id, ApproveProUpgradeRequest request);

    /**
     * Reject pro upgrade request (Agent only)
     */
    void rejectProUpgrade(Long id, RejectProUpgradeRequest request);

    /**
     * Suspend client account (Agent only)
     */
    void suspendClient(Long id, SuspendUserRequest request);

    /**
     * Reactivate suspended client (Agent only)
     */
    void reactivateClient(Long id, ReactivateUserRequest request);

    /**
     * Get client activity history (Agent only)
     */
    Page<UserActivityDto> getClientActivity(Long id, String activityType, Pageable pageable);

    /**
     * Add internal note to client profile (Agent only)
     */
    UserNoteDto addClientNote(Long id, CreateUserNoteRequest request);

    /**
     * Get client internal notes (Agent only)
     */
    Page<UserNoteDto> getClientNotes(Long id, Pageable pageable);

    /**
     * Send message to client (Agent only)
     */
    void sendMessageToClient(Long id, SendMessageRequest request);

    /**
     * Get agent performance statistics (Agent only)
     */
    AgentStatisticsDto getAgentStatistics(String period);

    /**
     * Get tasks assigned to agent (Agent only)
     */
    Page<TaskDto> getAssignedTasks(String status, String priority, Pageable pageable);

    /**
     * Update task status (Agent only)
     */
    void updateTaskStatus(Long taskId, UpdateTaskStatusRequest request);

    // ==================== CLIENT OPERATIONS ====================

    /**
     * Get current authenticated user's profile
     */
    UserProfileDto getCurrentUserProfile();

    /**
     * Update authenticated user's profile
     */
    UserProfileDto updateProfile(UpdateProfileRequest request);

    /**
     * Change user password
     */
    void changePassword(ChangePasswordRequest request);

    /**
     * Upload user avatar image
     */
    AvatarUploadResponseDto uploadAvatar(MultipartFile file);

    /**
     * Delete user avatar
     */
    void deleteAvatar();

    /**
     * Get profile verification status
     */
    ProfileVerificationStatusDto getVerificationStatus();

    /**
     * Submit profile for verification
     */
    void submitProfileVerification(SubmitVerificationRequest request);

    /**
     * Request pro upgrade (Standard client only)
     */
    void requestProUpgrade(ProUpgradeRequest request);

    /**
     * Get pro upgrade request status
     */
    ProUpgradeStatusDto getProUpgradeStatus();

    /**
     * Get dashboard statistics (Pro client only)
     */
    ClientDashboardStatsDto getDashboardStats(String period);

    /**
     * Get user notifications
     */
    Page<NotificationDto> getUserNotifications(String type, Boolean isRead, Pageable pageable);

    /**
     * Mark notification as read
     */
    void markNotificationAsRead(Long notificationId);

    /**
     * Get notification settings
     */
    NotificationSettingsDto getNotificationSettings();

    /**
     * Update notification settings
     */
    NotificationSettingsDto updateNotificationSettings(UpdateNotificationSettingsRequest request);

    /**
     * Get account activity history
     */
    Page<UserActivityDto> getAccountActivity(String activityType, String dateFrom,
                                             String dateTo, Pageable pageable);

    /**
     * Update privacy settings
     */
    PrivacySettingsDto updatePrivacySettings(UpdatePrivacySettingsRequest request);

    /**
     * Get privacy settings
     */
    PrivacySettingsDto getPrivacySettings();

    /**
     * Request account deletion (GDPR)
     */
    void requestAccountDeletion(DeleteAccountRequest request);

    /**
     * Export personal data (GDPR)
     */
    byte[] exportPersonalData(String format);

    // ==================== SECURITY OPERATIONS ====================

    /**
     * Get two-factor authentication status
     */
    TwoFactorStatusDto getTwoFactorStatus();

    /**
     * Enable two-factor authentication
     */
    TwoFactorSetupDto enableTwoFactor();

    /**
     * Confirm two-factor authentication setup
     */
    void confirmTwoFactor(ConfirmTwoFactorRequest request);

    /**
     * Disable two-factor authentication
     */
    void disableTwoFactor(DisableTwoFactorRequest request);

    /**
     * Generate backup codes for 2FA
     */
    BackupCodesDto generateBackupCodes();

    /**
     * Get connected sessions
     */
    Page<UserSessionDto> getConnectedSessions(Pageable pageable);

    /**
     * Revoke specific session
     */
    void revokeSession(String sessionId);

    /**
     * Revoke all sessions except current
     */
    void revokeAllSessions();
}