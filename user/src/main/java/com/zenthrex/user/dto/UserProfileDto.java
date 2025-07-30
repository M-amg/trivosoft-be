package com.zenthrex.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "User profile information")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserProfileDto(
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

        @Schema(description = "Avatar URL")
        String avatarUrl,

        @Schema(description = "Date of birth")
        String dateOfBirth,

        @Schema(description = "Address")
        String address,

        @Schema(description = "City")
        String city,

        @Schema(description = "Country")
        String country,

        @Schema(description = "User role")
        String role,

        @Schema(description = "Profile verification status")
        Boolean profileVerified,

        @Schema(description = "Two-factor authentication enabled")
        Boolean twoFactorEnabled,

        @Schema(description = "Account creation date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime createdAt
) {
}
