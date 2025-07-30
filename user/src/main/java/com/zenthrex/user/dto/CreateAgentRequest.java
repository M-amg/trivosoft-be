package com.zenthrex.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

@Schema(description = "Create agent request")
public record CreateAgentRequest(
        @Schema(description = "First name", example = "Jane")
        @NotBlank(message = "First name is required")
        @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
        String firstName,

        @Schema(description = "Last name", example = "Smith")
        @NotBlank(message = "Last name is required")
        @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
        String lastName,

        @Schema(description = "Email address", example = "jane.smith@company.com")
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @Schema(description = "Phone number", example = "+1234567890")
        @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number format")
        String phone,

        @Schema(description = "Temporary password", example = "TempPass123!")
        @NotBlank(message = "Password is required")
        @Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]",
                message = "Password must contain at least one lowercase, uppercase, digit, and special character")
        String password,

        @Schema(description = "Department", example = "Customer Support")
        String department,

        @Schema(description = "Initial permissions")
        List<String> permissions
) {
}
