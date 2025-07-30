package com.zenthrex.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Change password request")
public record ChangePasswordRequest(
        @Schema(description = "Current password", example = "oldPassword123!")
        @NotBlank(message = "Current password is required")
        String currentPassword,

        @Schema(description = "New password", example = "newPassword123!")
        @NotBlank(message = "New password is required")
        @Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]",
                message = "Password must contain at least one lowercase, uppercase, digit, and special character")
        String newPassword,

        @Schema(description = "Confirm new password", example = "newPassword123!")
        @NotBlank(message = "Password confirmation is required")
        String confirmPassword
) {
}
