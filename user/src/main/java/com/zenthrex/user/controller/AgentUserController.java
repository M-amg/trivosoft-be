package com.zenthrex.user.controller;

import com.zenthrex.user.api.AgentUserApi;
import com.zenthrex.user.dto.*;
import com.zenthrex.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@PreAuthorize("hasRole('AGENT')")
public class AgentUserController implements AgentUserApi {

    private final UserService userService;

    @Override
    public ResponseEntity<Page<UserDto>> getAllClients(String clientType, String status,
                                                       String search, Boolean verified,
                                                       Pageable pageable) {
        log.info("Agent fetching clients - type: {}, status: {}, search: {}, verified: {}",
                clientType, status, search, verified);
        Page<UserDto> clients = userService.getAllClients(clientType, status, search, verified, pageable);
        return ResponseEntity.ok(clients);
    }

    @Override
    public ResponseEntity<ClientDetailDto> getClientDetails(Long id) {
        log.info("Agent fetching client details for ID: {}", id);
        ClientDetailDto clientDetails = userService.getClientDetails(id);
        return ResponseEntity.ok(clientDetails);
    }

    @Override
    public ResponseEntity<ApiResponseDto> verifyClientProfile(Long id, VerifyProfileRequest request) {
        log.info("Agent verifying client profile: {} - verified: {}", id, request.verified());
        userService.verifyClientProfile(id, request);
        String message = request.verified() ? "Profile verified successfully" : "Profile verification rejected";
        return ResponseEntity.ok(new ApiResponseDto(message));
    }

    @Override
    public ResponseEntity<Page<ProfileVerificationDto>> getPendingVerifications(Pageable pageable) {
        log.info("Agent fetching pending profile verifications");
        Page<ProfileVerificationDto> verifications = userService.getPendingVerifications(pageable);
        return ResponseEntity.ok(verifications);
    }

    @Override
    public ResponseEntity<Page<ProUpgradeRequestDto>> getProUpgradeRequests(String status, Pageable pageable) {
        log.info("Agent fetching pro upgrade requests - status: {}", status);
        Page<ProUpgradeRequestDto> requests = userService.getProUpgradeRequests(status, pageable);
        return ResponseEntity.ok(requests);
    }

    @Override
    public ResponseEntity<ProUpgradeRequestDetailDto> getProUpgradeRequestDetails(Long id) {
        log.info("Agent fetching pro upgrade request details for ID: {}", id);
        ProUpgradeRequestDetailDto details = userService.getProUpgradeRequestDetails(id);
        return ResponseEntity.ok(details);
    }

    @Override
    public ResponseEntity<ApiResponseDto> approveProUpgrade(Long id, ApproveProUpgradeRequest request) {
        log.info("Agent approving pro upgrade request: {}", id);
        userService.approveProUpgrade(id, request);
        return ResponseEntity.ok(new ApiResponseDto("Pro upgrade approved successfully"));
    }

    @Override
    public ResponseEntity<ApiResponseDto> rejectProUpgrade(Long id, RejectProUpgradeRequest request) {
        log.info("Agent rejecting pro upgrade request: {} - reason: {}", id, request.reason());
        userService.rejectProUpgrade(id, request);
        return ResponseEntity.ok(new ApiResponseDto("Pro upgrade rejected"));
    }

    @Override
    public ResponseEntity<ApiResponseDto> suspendClient(Long id, SuspendUserRequest request) {
        log.info("Agent suspending client: {} - reason: {}", id, request.reason());
        userService.suspendClient(id, request);
        return ResponseEntity.ok(new ApiResponseDto("Client account suspended"));
    }

    @Override
    public ResponseEntity<ApiResponseDto> reactivateClient(Long id, ReactivateUserRequest request) {
        log.info("Agent reactivating client: {}", id);
        userService.reactivateClient(id, request);
        return ResponseEntity.ok(new ApiResponseDto("Client account reactivated"));
    }

    @Override
    public ResponseEntity<Page<UserActivityDto>> getClientActivity(Long id, String activityType, Pageable pageable) {
        log.info("Agent fetching client {} activity - type: {}", id, activityType);
        Page<UserActivityDto> activity = userService.getClientActivity(id, activityType, pageable);
        return ResponseEntity.ok(activity);
    }

    @Override
    public ResponseEntity<UserNoteDto> addClientNote(Long id, CreateUserNoteRequest request) {
        log.info("Agent adding note to client: {}", id);
        UserNoteDto note = userService.addClientNote(id, request);
        return ResponseEntity.ok(note);
    }

    @Override
    public ResponseEntity<Page<UserNoteDto>> getClientNotes(Long id, Pageable pageable) {
        log.info("Agent fetching notes for client: {}", id);
        Page<UserNoteDto> notes = userService.getClientNotes(id, pageable);
        return ResponseEntity.ok(notes);
    }

    @Override
    public ResponseEntity<ApiResponseDto> sendMessageToClient(Long id, SendMessageRequest request) {
        log.info("Agent sending message to client: {}", id);
        userService.sendMessageToClient(id, request);
        return ResponseEntity.ok(new ApiResponseDto("Message sent successfully"));
    }

    @Override
    public ResponseEntity<AgentStatisticsDto> getAgentStatistics(String period) {
        log.info("Agent fetching their statistics for period: {}", period);
        AgentStatisticsDto statistics = userService.getAgentStatistics(period);
        return ResponseEntity.ok(statistics);
    }

    @Override
    public ResponseEntity<Page<TaskDto>> getAssignedTasks(String status, String priority, Pageable pageable) {
        log.info("Agent fetching assigned tasks - status: {}, priority: {}", status, priority);
        Page<TaskDto> tasks = userService.getAssignedTasks(status, priority, pageable);
        return ResponseEntity.ok(tasks);
    }

    @Override
    public ResponseEntity<ApiResponseDto> updateTaskStatus(Long taskId, UpdateTaskStatusRequest request) {
        log.info("Agent updating task {} status to: {}", taskId, request.status());
        userService.updateTaskStatus(taskId, request);
        return ResponseEntity.ok(new ApiResponseDto("Task status updated successfully"));
    }
}