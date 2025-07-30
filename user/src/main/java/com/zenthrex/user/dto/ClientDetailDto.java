package com.zenthrex.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Client detailed information")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ClientDetailDto(
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

        @Schema(description = "Client type")
        String clientType,

        @Schema(description = "Account status")
        String status,

        @Schema(description = "Profile verification status")
        Boolean profileVerified,

        @Schema(description = "Avatar URL")
        String avatarUrl,

        @Schema(description = "Total bookings count")
        Integer totalBookings,

        @Schema(description = "Total orders count")
        Integer totalOrders,

        @Schema(description = "Total spent amount")
        String totalSpent,

        @Schema(description = "Registration date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime createdAt,

        @Schema(description = "Last activity date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime lastActivityAt,

        @Schema(description = "Internal notes count")
        Integer notesCount
) {
}
