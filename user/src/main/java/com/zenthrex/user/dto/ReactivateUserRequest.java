package com.zenthrex.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Reactivate user request")
public record ReactivateUserRequest(
        @Schema(description = "Reactivation notes", example = "Issue resolved, account reactivated")
        String notes
) {
}
