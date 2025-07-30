package com.zenthrex.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Bulk update operations")
public record BulkUpdateOperationsDto(
        @Schema(description = "Status to set")
        String status,

        @Schema(description = "Role to set")
        String role,

        @Schema(description = "Verification status to set")
        Boolean verified,

        @Schema(description = "Tags to add")
        List<String> addTags,

        @Schema(description = "Tags to remove")
        List<String> removeTags
) {
}
