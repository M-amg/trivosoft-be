package com.zenthrex.user.controller;

import com.zenthrex.user.api.AdminUserApi;
import com.zenthrex.user.dto.*;
import com.zenthrex.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@PreAuthorize("hasRole('ADMIN')")
public class AdminUserController implements AdminUserApi {

    private final UserService userService;

    @Override
    public ResponseEntity<Page<UserDto>> getAllUsers(String role, String status, String search, Pageable pageable) {
        log.info("Admin fetching users - role: {}, status: {}, search: {}", role, status, search);
        Page<UserDto> users = userService.getAllUsers(role, status, search, pageable);
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<UserDetailDto> getUserById(Long id) {
        log.info("Admin fetching user details for ID: {}", id);
        UserDetailDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<ApiResponseDto> updateUserStatus(Long id, UpdateUserStatusRequest request) {
        log.info("Admin updating user {} status to: {}", id, request.status());
        userService.updateUserStatus(id, request);
        return ResponseEntity.ok(new ApiResponseDto("User status updated successfully"));
    }

    @Override
    public ResponseEntity<ApiResponseDto> updateUserRole(Long id, UpdateUserRoleRequest request) {
        log.info("Admin updating user {} role to: {}", id, request.role());
        userService.updateUserRole(id, request);
        return ResponseEntity.ok(new ApiResponseDto("User role updated successfully"));
    }

    @Override
    public ResponseEntity<ApiResponseDto> deleteUser(Long id) {
        log.info("Admin deleting user: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.ok(new ApiResponseDto("User deleted successfully"));
    }

    @Override
    public ResponseEntity<UserDto> createAgent(CreateAgentRequest request) {
        log.info("Admin creating new agent with email: {}", request.email());
        UserDto agent = userService.createAgent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(agent);
    }

    @Override
    public ResponseEntity<Page<UserDto>> searchUsers(String query, String role, String status,
                                                     String registeredFrom, String registeredTo,
                                                     Pageable pageable) {
        log.info("Admin searching users with query: {}", query);
        Page<UserDto> users = userService.searchUsers(query, role, status, registeredFrom, registeredTo, pageable);
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<UserStatisticsDto> getUserStatistics(String period) {
        log.info("Admin fetching user statistics for period: {}", period);
        UserStatisticsDto statistics = userService.getUserStatistics(period);
        return ResponseEntity.ok(statistics);
    }

    @Override
    public ResponseEntity<byte[]> exportUsers(String format, String role, String status) {
        log.info("Admin exporting users - format: {}, role: {}, status: {}", format, role, status);
        byte[] data = userService.exportUsers(format, role, status);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=users." + format.toLowerCase())
                .body(data);
    }

    @Override
    public ResponseEntity<BulkUpdateResultDto> bulkUpdateUsers(BulkUpdateUsersRequest request) {
        log.info("Admin bulk updating {} users", request.userIds().size());
        BulkUpdateResultDto result = userService.bulkUpdateUsers(request);
        return ResponseEntity.ok(result);
    }
}