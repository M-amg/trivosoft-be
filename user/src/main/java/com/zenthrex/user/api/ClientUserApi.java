package com.zenthrex.user.api;

import com.zenthrex.user.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Client User Management API
 * Contains all client-specific user operations (both Standard and Pro clients)
 * Module: user-api
 */
@Tag(name = "Client User Management", description = "Client profile and account management operations")
@RequestMapping("/api/v1/client/profile")
@SecurityRequirement(name = "bearerAuth")
public interface ClientUserApi {

    @Operation(
            summary = "Get current user profile",
            description = "Retrieve authenticated client's profile information. Requires CLIENT_STANDARD or CLIENT_PRO role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Profile retrieved successfully",
                    content = @Content(schema = @Schema(implementation = UserProfileDto.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized - Invalid or missing Bearer token"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden - Client role required"
            )
    })
    @GetMapping
    ResponseEntity<UserProfileDto> getCurrentUserProfile();

    @Operation(
            summary = "Update user profile",
            description = "Update authenticated client's profile information. Requires CLIENT_STANDARD or CLIENT_PRO role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Profile updated successfully",
                    content = @Content(schema = @Schema(implementation = UserProfileDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid profile data"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Email already exists"
            )
    })
    @PutMapping
    ResponseEntity<UserProfileDto> updateProfile(
            @Parameter(description = "Profile update request", required = true)
            @Valid @RequestBody UpdateProfileRequest request
    );

    @Operation(
            summary = "Change password",
            description = "Change authenticated client's password. Requires CLIENT_STANDARD or CLIENT_PRO role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Password changed successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid current password or password requirements not met"
            )
    })
    @PutMapping("/change-password")
    ResponseEntity<ApiResponseDto> changePassword(
            @Parameter(description = "Password change request", required = true)
            @Valid @RequestBody ChangePasswordRequest request
    );

    @Operation(
            summary = "Upload profile avatar",
            description = "Upload or update client's profile picture. Requires CLIENT_STANDARD or CLIENT_PRO role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Avatar uploaded successfully",
                    content = @Content(schema = @Schema(implementation = AvatarUploadResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid file format or size"
            )
    })
    @PostMapping("/avatar")
    ResponseEntity<AvatarUploadResponseDto> uploadAvatar(
            @Parameter(
                    description = "Avatar image file (JPEG, PNG, max 5MB)",
                    required = true
            )
            @RequestParam("file") MultipartFile file
    );

    @Operation(
            summary = "Delete profile avatar",
            description = "Remove client's profile picture. Requires CLIENT_STANDARD or CLIENT_PRO role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @DeleteMapping("/avatar")
    ResponseEntity<ApiResponseDto> deleteAvatar();

    @Operation(
            summary = "Get profile verification status",
            description = "Get current profile verification status and requirements. Requires CLIENT_STANDARD or CLIENT_PRO role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/verification-status")
    ResponseEntity<ProfileVerificationStatusDto> getVerificationStatus();

    @Operation(
            summary = "Submit profile verification",
            description = "Submit profile for verification with required documents. Requires CLIENT_STANDARD or CLIENT_PRO role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PostMapping("/verification")
    ResponseEntity<ApiResponseDto> submitProfileVerification(
            @Parameter(description = "Profile verification request", required = true)
            @Valid @RequestBody SubmitVerificationRequest request
    );

    @Operation(
            summary = "Request pro upgrade",
            description = "Submit request to upgrade from Standard to Pro client. Requires CLIENT_STANDARD role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Pro upgrade request submitted successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "User already has pro status or pending request exists"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Only standard clients can request pro upgrade"
            )
    })
    @PostMapping("/request-pro-upgrade")
    ResponseEntity<ApiResponseDto> requestProUpgrade(
            @Parameter(description = "Pro upgrade request", required = true)
            @Valid @RequestBody ProUpgradeRequest request
    );

    @Operation(
            summary = "Get pro upgrade status",
            description = "Check status of pro upgrade request. Requires CLIENT_STANDARD role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/pro-upgrade-status")
    ResponseEntity<ProUpgradeStatusDto> getProUpgradeStatus();

    @Operation(
            summary = "Get dashboard statistics",
            description = "Retrieve client dashboard statistics and KPIs. Requires CLIENT_PRO role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Dashboard statistics retrieved successfully",
                    content = @Content(schema = @Schema(implementation = ClientDashboardStatsDto.class))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Pro client role required"
            )
    })
    @GetMapping("/dashboard/stats")
    ResponseEntity<ClientDashboardStatsDto> getDashboardStats(
            @Parameter(description = "Period for statistics")
            @RequestParam(defaultValue = "MONTHLY") String period
    );

    @Operation(
            summary = "Get user notifications",
            description = "Retrieve client's notifications with pagination. Requires CLIENT_STANDARD or CLIENT_PRO role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/notifications")
    ResponseEntity<Page<NotificationDto>> getNotifications(
            @Parameter(description = "Filter by notification type")
            @RequestParam(required = false) String type,

            @Parameter(description = "Filter by read status")
            @RequestParam(required = false) Boolean isRead,

            @Parameter(hidden = true) Pageable pageable
    );

    @Operation(
            summary = "Mark notification as read",
            description = "Mark specific notification as read. Requires CLIENT_STANDARD or CLIENT_PRO role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PutMapping("/notifications/{id}/read")
    ResponseEntity<ApiResponseDto> markNotificationAsRead(
            @Parameter(description = "Notification ID", required = true)
            @PathVariable Long id
    );

    @Operation(
            summary = "Get notification settings",
            description = "Retrieve client's notification preferences. Requires CLIENT_STANDARD or CLIENT_PRO role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/notification-settings")
    ResponseEntity<NotificationSettingsDto> getNotificationSettings();

    @Operation(
            summary = "Update notification settings",
            description = "Update client's notification preferences. Requires CLIENT_STANDARD or CLIENT_PRO role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PutMapping("/notification-settings")
    ResponseEntity<NotificationSettingsDto> updateNotificationSettings(
            @Parameter(description = "Notification settings update", required = true)
            @Valid @RequestBody UpdateNotificationSettingsRequest request
    );

    @Operation(
            summary = "Get account activity",
            description = "Retrieve client's account activity history. Requires CLIENT_STANDARD or CLIENT_PRO role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/activity")
    ResponseEntity<Page<UserActivityDto>> getAccountActivity(
            @Parameter(description = "Activity type filter")
            @RequestParam(required = false) String activityType,

            @Parameter(description = "Date from (ISO format)")
            @RequestParam(required = false) String dateFrom,

            @Parameter(description = "Date to (ISO format)")
            @RequestParam(required = false) String dateTo,

            @Parameter(hidden = true) Pageable pageable
    );

    @Operation(
            summary = "Update privacy settings",
            description = "Update client's privacy and data sharing preferences. Requires CLIENT_STANDARD or CLIENT_PRO role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PutMapping("/privacy-settings")
    ResponseEntity<PrivacySettingsDto> updatePrivacySettings(
            @Parameter(description = "Privacy settings update", required = true)
            @Valid @RequestBody UpdatePrivacySettingsRequest request
    );

    @Operation(
            summary = "Get privacy settings",
            description = "Retrieve client's current privacy settings. Requires CLIENT_STANDARD or CLIENT_PRO role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/privacy-settings")
    ResponseEntity<PrivacySettingsDto> getPrivacySettings();

    @Operation(
            summary = "Delete account",
            description = "Request account deletion (GDPR compliance). Requires CLIENT_STANDARD or CLIENT_PRO role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Account deletion request submitted"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Account has pending obligations"
            )
    })
    @DeleteMapping("/delete-account")
    ResponseEntity<ApiResponseDto> requestAccountDeletion(
            @Parameter(description = "Account deletion request", required = true)
            @Valid @RequestBody DeleteAccountRequest request
    );

    @Operation(
            summary = "Export personal data",
            description = "Export all personal data (GDPR compliance). Requires CLIENT_STANDARD or CLIENT_PRO role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/export-data")
    ResponseEntity<byte[]> exportPersonalData(
            @Parameter(description = "Export format")
            @RequestParam(defaultValue = "JSON") String format
    );

    @Operation(
            summary = "Get two-factor authentication status",
            description = "Check if 2FA is enabled for the account. Requires CLIENT_STANDARD or CLIENT_PRO role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/2fa/status")
    ResponseEntity<TwoFactorStatusDto> getTwoFactorStatus();

    @Operation(
            summary = "Enable two-factor authentication",
            description = "Enable 2FA for enhanced account security. Requires CLIENT_STANDARD or CLIENT_PRO role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PostMapping("/2fa/enable")
    ResponseEntity<TwoFactorSetupDto> enableTwoFactor();

    @Operation(
            summary = "Confirm two-factor authentication setup",
            description = "Confirm 2FA setup with verification code. Requires CLIENT_STANDARD or CLIENT_PRO role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PostMapping("/2fa/confirm")
    ResponseEntity<ApiResponseDto> confirmTwoFactor(
            @Parameter(description = "2FA confirmation request", required = true)
            @Valid @RequestBody ConfirmTwoFactorRequest request
    );

    @Operation(
            summary = "Disable two-factor authentication",
            description = "Disable 2FA for the account. Requires CLIENT_STANDARD or CLIENT_PRO role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @DeleteMapping("/2fa/disable")
    ResponseEntity<ApiResponseDto> disableTwoFactor(
            @Parameter(description = "2FA disable request", required = true)
            @Valid @RequestBody DisableTwoFactorRequest request
    );

    @Operation(
            summary = "Generate backup codes",
            description = "Generate backup codes for 2FA recovery. Requires CLIENT_STANDARD or CLIENT_PRO role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PostMapping("/2fa/backup-codes")
    ResponseEntity<BackupCodesDto> generateBackupCodes();

    @Operation(
            summary = "Get connected sessions",
            description = "View all active sessions and devices. Requires CLIENT_STANDARD or CLIENT_PRO role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/sessions")
    ResponseEntity<Page<UserSessionDto>> getConnectedSessions(
            @Parameter(hidden = true) Pageable pageable
    );

    @Operation(
            summary = "Revoke session",
            description = "Revoke specific session/device access. Requires CLIENT_STANDARD or CLIENT_PRO role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @DeleteMapping("/sessions/{sessionId}")
    ResponseEntity<ApiResponseDto> revokeSession(
            @Parameter(description = "Session ID", required = true)
            @PathVariable String sessionId
    );

    @Operation(
            summary = "Revoke all sessions",
            description = "Revoke all sessions except current one. Requires CLIENT_STANDARD or CLIENT_PRO role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @DeleteMapping("/sessions/revoke-all")
    ResponseEntity<ApiResponseDto> revokeAllSessions();
}