package com.zenthrex.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Approve pro upgrade request")
public record ApproveProUpgradeRequest(
        @Schema(description = "Approval notes", example = "All documents verified and approved")
        String notes
) {
}
