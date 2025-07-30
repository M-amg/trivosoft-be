package com.zenthrex.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Schema(description = "Bulk update users request")
public record BulkUpdateUsersRequest(
        @Schema(description = "User IDs to update")
        @NotEmpty(message = "User IDs list cannot be empty")
        List<Long> userIds,

        @Schema(description = "Update operations")
        @NotNull(message = "Update operations are required")
        BulkUpdateOperationsDto operations
) {
}
