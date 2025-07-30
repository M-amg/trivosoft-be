package com.zenthrex.user.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "User basic information")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDto(
        @Schema(description = "User ID", example = "1")
        Long id,

        @Schema(description = "First name", example = "John")
        String firstName,

        @Schema(description = "Last name", example = "Doe")
        String lastName,

        @Schema(description = "Email address", example = "john.doe@example.com")
        String email,

        @Schema(description = "Phone number", example = "+1234567890")
        String phone,

        @Schema(description = "User role", example = "CLIENT_PRO")
        String role,

        @Schema(description = "Account status", example = "ACTIVE")
        String status,

        @Schema(description = "Profile verification status")
        Boolean profileVerified,

        @Schema(description = "Avatar URL")
        String avatarUrl,

        @Schema(description = "Registration date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime createdAt,

        @Schema(description = "Last update date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime updatedAt
) {
}