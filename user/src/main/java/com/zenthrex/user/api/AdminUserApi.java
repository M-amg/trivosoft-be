package com.zenthrex.user.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Admin User Management API
 * Contains all admin-specific user management operations
 * Module: user-api
 */
@Tag(name = "Admin User Management", description = "Administrative user management operations")
@RequestMapping("/api/v1/admin/users")
@SecurityRequirement(name = "bearerAuth")
public interface AdminUserApi {

    @Operation(
            summary = "Get all users",
            description = "Retrieve paginated list of all users with filtering options. Requires ADMIN role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Users retrieved successfully",
                    content = @Content(schema = @Schema(implementation = Page.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized - Invalid or missing Bearer token",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden - ADMIN role required",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @GetMapping
    ResponseEntity<Page<UserDto>> getAllUsers(
            @Parameter(
                    description = "Filter by user role",
                    example = "CLIENT_PRO",
                    schema = @Schema(allowableValues = {"ADMIN", "AGENT", "CLIENT_PRO", "CLIENT_STANDARD"})
            )
            @RequestParam(required = false) String role,

            @Parameter(
                    description = "Filter by user status",
                    example = "ACTIVE",
                    schema = @Schema(allowableValues = {"ACTIVE", "INACTIVE", "PENDING", "SUSPENDED"})
            )
            @RequestParam(required = false) String status,

            @Parameter(
                    description = "Search by name or email",
                    example = "john@example.com"
            )
            @RequestParam(required = false) String search,

            @Parameter(hidden = true) Pageable pageable
    );

    @Operation(
            summary = "Get user by ID",
            description = "Retrieve detailed information about a specific user. Requires ADMIN role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User retrieved successfully",
                    content = @Content(schema = @Schema(implementation = UserDetailDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @GetMapping("/{id}")
    ResponseEntity<UserDetailDto> getUserById(
            @Parameter(description = "User ID", required = true, example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "Update user status",
            description = "Update user account status (activate, deactivate, suspend). Requires ADMIN role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User status updated successfully",
                    content = @Content(schema = @Schema(implementation = ApiResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request data",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found"
            )
    })
    @PutMapping("/{id}/status")
    ResponseEntity<ApiResponseDto> updateUserStatus(
            @Parameter(description = "User ID", required = true)
            @PathVariable Long id,

            @Parameter(description = "Status update request", required = true)
            @Valid @RequestBody UpdateUserStatusRequest request
    );

    @Operation(
            summary = "Update user role",
            description = "Change user role and permissions. Requires ADMIN role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PutMapping("/{id}/role")
    ResponseEntity<ApiResponseDto> updateUserRole(
            @Parameter(description = "User ID", required = true)
            @PathVariable Long id,

            @Parameter(description = "Role update request", required = true)
            @Valid @RequestBody UpdateUserRoleRequest request
    );

    @Operation(
            summary = "Delete user",
            description = "Soft delete user account. Cannot delete other admin users. Requires ADMIN role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Cannot delete admin user"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found"
            )
    })
    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponseDto> deleteUser(
            @Parameter(description = "User ID", required = true)
            @PathVariable Long id
    );

    @Operation(
            summary = "Create agent account",
            description = "Create new agent account with administrative privileges. Requires ADMIN role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Agent created successfully",
                    content = @Content(schema = @Schema(implementation = UserDto.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "User already exists with this email",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @PostMapping("/agents")
    ResponseEntity<UserDto> createAgent(
            @Parameter(description = "Agent creation request", required = true)
            @Valid @RequestBody CreateAgentRequest request
    );

    @Operation(
            summary = "Search users",
            description = "Advanced user search with multiple criteria. Requires ADMIN role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/search")
    ResponseEntity<Page<UserDto>> searchUsers(
            @Parameter(description = "Search query", required = true)
            @RequestParam String query,

            @Parameter(description = "Role filter")
            @RequestParam(required = false) String role,

            @Parameter(description = "Status filter")
            @RequestParam(required = false) String status,

            @Parameter(description = "Registration date from (ISO format)")
            @RequestParam(required = false) String registeredFrom,

            @Parameter(description = "Registration date to (ISO format)")
            @RequestParam(required = false) String registeredTo,

            @Parameter(hidden = true) Pageable pageable
    );

    @Operation(
            summary = "Get user statistics",
            description = "Retrieve comprehensive user statistics and analytics. Requires ADMIN role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/statistics")
    ResponseEntity<UserStatisticsDto> getUserStatistics(
            @Parameter(description = "Period for statistics (DAILY, WEEKLY, MONTHLY)")
            @RequestParam(defaultValue = "MONTHLY") String period
    );

    @Operation(
            summary = "Export users data",
            description = "Export users data to CSV or Excel format. Requires ADMIN role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/export")
    ResponseEntity<byte[]> exportUsers(
            @Parameter(description = "Export format")
            @RequestParam(defaultValue = "CSV") String format,

            @Parameter(description = "Role filter")
            @RequestParam(required = false) String role,

            @Parameter(description = "Status filter")
            @RequestParam(required = false) String status
    );

    @Operation(
            summary = "Bulk update users",
            description = "Update multiple users at once. Requires ADMIN role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PutMapping("/bulk-update")
    ResponseEntity<BulkUpdateResultDto> bulkUpdateUsers(
            @Parameter(description = "Bulk update request", required = true)
            @Valid @RequestBody BulkUpdateUsersRequest request
    );
}