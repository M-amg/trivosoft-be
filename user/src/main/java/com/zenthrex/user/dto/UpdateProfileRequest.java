package com.zenthrex.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Update profile request")
public record UpdateProfileRequest(
        @Schema(description = "First name", example = "John")
        @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
        String firstName,

        @Schema(description = "Last name", example = "Doe")
        @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
        String lastName,

        @Schema(description = "Phone number", example = "+1234567890")
        @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number format")
        String phone,

        @Schema(description = "Date of birth", example = "1990-01-15")
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date format should be YYYY-MM-DD")
        String dateOfBirth,

        @Schema(description = "Address", example = "123 Main St")
        @Size(max = 255, message = "Address must not exceed 255 characters")
        String address,

        @Schema(description = "City", example = "New York")
        @Size(max = 100, message = "City must not exceed 100 characters")
        String city,

        @Schema(description = "Country", example = "USA")
        @Size(max = 100, message = "Country must not exceed 100 characters")
        String country
) {
}
