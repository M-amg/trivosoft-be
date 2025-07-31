package com.zenthrex.user.api;

import com.zenthrex.user.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * Agent User Management API
 * Contains all agent-specific user management operations
 * Module: user-api
 */
@Tag(name = "Agent User Management", description = "Agent user management and moderation operations")
@RequestMapping("/api/v1/agent/users")
@SecurityRequirement(name = "bearerAuth")
public interface AgentUserApi {

    @Operation(
            summary = "Get all clients",
            description = "Retrieve paginated list of all client accounts with filtering options. Requires AGENT role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Clients retrieved successfully",
                    content = @Content(schema = @Schema(implementation = Page.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized - Invalid or missing Bearer token"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden - AGENT role required"
            )
    })
    @GetMapping("/clients")
    ResponseEntity<Page<UserDto>> getAllClients(
            @Parameter(
                    description = "Filter by client type",
                    schema = @Schema(allowableValues = {"PRO", "STANDARD"})
            )
            @RequestParam(required = false) String clientType,

            @Parameter(
                    description = "Filter by account status",
                    schema = @Schema(allowableValues = {"ACTIVE", "INACTIVE", "PENDING", "SUSPENDED"})
            )
            @RequestParam(required = false) String status,

            @Parameter(
                    description = "Search by name, email, or phone"
            )
            @RequestParam(required = false) String search,

            @Parameter(
                    description = "Filter by verification status"
            )
            @RequestParam(required = false) Boolean verified,

            @Parameter(hidden = true) Pageable pageable
    );

    @Operation(
            summary = "Get client details",
            description = "Retrieve detailed information about a specific client. Requires AGENT role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/clients/{id}")
    ResponseEntity<ClientDetailDto> getClientDetails(
            @Parameter(description = "Client ID", required = true)
            @PathVariable Long id
    );

    @Operation(
            summary = "Verify client profile",
            description = "Verify or reject client profile verification request. Requires AGENT role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Profile verification updated successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid verification request"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Client not found"
            )
    })
    @PutMapping("/clients/{id}/verify")
    ResponseEntity<ApiResponseDto> verifyClientProfile(
            @Parameter(description = "Client ID", required = true)
            @PathVariable Long id,

            @Parameter(description = "Profile verification request", required = true)
            @Valid @RequestBody VerifyProfileRequest request
    );

    @Operation(
            summary = "Get pending verification requests",
            description = "Retrieve all pending client profile verification requests. Requires AGENT role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/verifications/pending")
    ResponseEntity<Page<ProfileVerificationDto>> getPendingVerifications(
            @Parameter(hidden = true) Pageable pageable
    );

    @Operation(
            summary = "Get pro upgrade requests",
            description = "Retrieve all pending client pro upgrade requests. Requires AGENT role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Pro upgrade requests retrieved successfully",
                    content = @Content(schema = @Schema(implementation = List.class))
            )
    })
    @GetMapping("/pro-requests")
    ResponseEntity<Page<ProUpgradeRequestDto>> getProUpgradeRequests(
            @Parameter(
                    description = "Filter by request status",
                    schema = @Schema(allowableValues = {"PENDING", "APPROVED", "REJECTED"})
            )
            @RequestParam(required = false) String status,

            @Parameter(hidden = true) Pageable pageable
    );

    @Operation(
            summary = "Get pro upgrade request details",
            description = "Retrieve detailed information about a specific pro upgrade request. Requires AGENT role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/pro-requests/{id}")
    ResponseEntity<ProUpgradeRequestDetailDto> getProUpgradeRequestDetails(
            @Parameter(description = "Request ID", required = true)
            @PathVariable Long id
    );

    @Operation(
            summary = "Approve pro upgrade",
            description = "Approve client pro upgrade request and upgrade their account. Requires AGENT role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Pro upgrade approved successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Request already processed or invalid"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Request not found"
            )
    })
    @PutMapping("/pro-requests/{id}/approve")
    ResponseEntity<ApiResponseDto> approveProUpgrade(
            @Parameter(description = "Request ID", required = true)
            @PathVariable Long id,

            @Parameter(description = "Approval request with notes", required = true)
            @Valid @RequestBody ApproveProUpgradeRequest request
    );

    @Operation(
            summary = "Reject pro upgrade",
            description = "Reject client pro upgrade request with reason. Requires AGENT role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PutMapping("/pro-requests/{id}/reject")
    ResponseEntity<ApiResponseDto> rejectProUpgrade(
            @Parameter(description = "Request ID", required = true)
            @PathVariable Long id,

            @Parameter(description = "Rejection request with reason", required = true)
            @Valid @RequestBody RejectProUpgradeRequest request
    );

    @Operation(
            summary = "Suspend client account",
            description = "Temporarily suspend client account with reason. Requires AGENT role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PutMapping("/clients/{id}/suspend")
    ResponseEntity<ApiResponseDto> suspendClient(
            @Parameter(description = "Client ID", required = true)
            @PathVariable Long id,

            @Parameter(description = "Suspension request with reason", required = true)
            @Valid @RequestBody SuspendUserRequest request
    );

    @Operation(
            summary = "Reactivate client account",
            description = "Reactivate suspended client account. Requires AGENT role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PutMapping("/clients/{id}/reactivate")
    ResponseEntity<ApiResponseDto> reactivateClient(
            @Parameter(description = "Client ID", required = true)
            @PathVariable Long id,

            @Parameter(description = "Reactivation notes")
            @RequestBody(required = false) ReactivateUserRequest request
    );

    @Operation(
            summary = "Get client activity history",
            description = "Retrieve client's activity and interaction history. Requires AGENT role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/clients/{id}/activity")
    ResponseEntity<Page<UserActivityDto>> getClientActivity(
            @Parameter(description = "Client ID", required = true)
            @PathVariable Long id,

            @Parameter(description = "Activity type filter")
            @RequestParam(required = false) String activityType,

            @Parameter(hidden = true) Pageable pageable
    );

    @Operation(
            summary = "Add note to client profile",
            description = "Add internal note to client profile for agent reference. Requires AGENT role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PostMapping("/clients/{id}/notes")
    ResponseEntity<UserNoteDto> addClientNote(
            @Parameter(description = "Client ID", required = true)
            @PathVariable Long id,

            @Parameter(description = "Note creation request", required = true)
            @Valid @RequestBody CreateUserNoteRequest request
    );

    @Operation(
            summary = "Get client notes",
            description = "Retrieve all internal notes for a client. Requires AGENT role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/clients/{id}/notes")
    ResponseEntity<Page<UserNoteDto>> getClientNotes(
            @Parameter(description = "Client ID", required = true)
            @PathVariable Long id,

            @Parameter(hidden = true) Pageable pageable
    );

    @Operation(
            summary = "Send message to client",
            description = "Send direct message to client for support or information. Requires AGENT role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PostMapping("/clients/{id}/message")
    ResponseEntity<ApiResponseDto> sendMessageToClient(
            @Parameter(description = "Client ID", required = true)
            @PathVariable Long id,

            @Parameter(description = "Message request", required = true)
            @Valid @RequestBody SendMessageRequest request
    );

    @Operation(
            summary = "Get agent statistics",
            description = "Retrieve agent's performance statistics and metrics. Requires AGENT role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/statistics")
    ResponseEntity<AgentStatisticsDto> getAgentStatistics(
            @Parameter(description = "Period for statistics")
            @RequestParam(defaultValue = "MONTHLY") String period
    );

    @Operation(
            summary = "Get assigned tasks",
            description = "Retrieve tasks assigned to the agent. Requires AGENT role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/tasks")
    ResponseEntity<Page<TaskDto>> getAssignedTasks(
            @Parameter(description = "Task status filter")
            @RequestParam(required = false) String status,

            @Parameter(description = "Task priority filter")
            @RequestParam(required = false) String priority,

            @Parameter(hidden = true) Pageable pageable
    );

    @Operation(
            summary = "Update task status",
            description = "Update status of assigned task. Requires AGENT role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PutMapping("/tasks/{taskId}/status")
    ResponseEntity<ApiResponseDto> updateTaskStatus(
            @Parameter(description = "Task ID", required = true)
            @PathVariable Long taskId,

            @Parameter(description = "Task status update", required = true)
            @Valid @RequestBody UpdateTaskStatusRequest request
    );
}