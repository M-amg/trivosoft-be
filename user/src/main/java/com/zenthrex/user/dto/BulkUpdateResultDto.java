package com.zenthrex.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Bulk update result")
public record BulkUpdateResultDto(
        @Schema(description = "Total processed")
        Integer totalProcessed,

        @Schema(description = "Successfully updated")
        Integer successCount,

        @Schema(description = "Failed updates")
        Integer failureCount,

        @Schema(description = "Error details")
        List<BulkUpdateErrorDto> errors
) {
}
