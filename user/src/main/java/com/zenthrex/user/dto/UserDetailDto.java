package com.zenthrex.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * Basic User DTOs for User Module
 * Using Java 17 records for immutability and better performance
 * Designed for MapStruct mapping
 */

// ==================== CORE USER DTOs ====================


@Schema(description = "Detailed user information")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDetailDto(
        @Schema(description = "User ID")
        Long id,

        @Schema(description = "First name")
        String firstName,

        @Schema(description = "Last name")
        String lastName,

        @Schema(description = "Email address")
        String email,

        @Schema(description = "Phone number")
        String phone,

        @Schema(description = "User role")
        String role,

        @Schema(description = "Account status")
        String status,

        @Schema(description = "Status reason")
        String statusReason,

        @Schema(description = "Profile verification status")
        Boolean profileVerified,

        @Schema(description = "Verification notes")
        String verificationNotes,

        @Schema(description = "Avatar URL")
        String avatarUrl,

        @Schema(description = "Date of birth")
        @JsonFormat(pattern = "yyyy-MM-dd")
        String dateOfBirth,

        @Schema(description = "Address")
        String address,

        @Schema(description = "City")
        String city,

        @Schema(description = "Country")
        String country,

        @Schema(description = "Registration date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime createdAt,

        @Schema(description = "Last update date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime updatedAt,

        @Schema(description = "Last login date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime lastLoginAt,

        @Schema(description = "Verification date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime verifiedAt,

        @Schema(description = "Verified by user ID")
        Long verifiedBy
) {
}
