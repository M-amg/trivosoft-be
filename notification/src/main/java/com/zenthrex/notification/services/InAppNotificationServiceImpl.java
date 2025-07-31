package com.zenthrex.notification.services;

import com.zenthrex.core.entites.user.User;
import com.zenthrex.core.entites.user.UserNotification;
import com.zenthrex.core.enums.NotificationPriority;
import com.zenthrex.core.enums.NotificationType;
import com.zenthrex.core.repositories.UserNotificationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class InAppNotificationServiceImpl implements InAppNotificationService {

    private final UserNotificationRepository notificationRepository;

    @Override
    public void createNotification(User user, String title, String message, String type) {
        createNotification(user, title, message, type, null);
    }

    @Override
    public void createNotification(User user, String title, String message, String type, String actionUrl) {
        try {
            UserNotification notification = UserNotification.builder()
                    .user(user)
                    .title(title)
                    .message(message)
                    .notificationType(NotificationType.valueOf(type.toUpperCase()))
                    .priority(NotificationPriority.NORMAL)
                    .isRead(false)
                    .actionUrl(actionUrl)
                    .build();

            notificationRepository.save(notification);
            log.info("In-app notification created for user: {} - type: {}", user.getEmail(), type);
        } catch (Exception e) {
            log.error("Failed to create in-app notification for user: {}", user.getEmail(), e);
        }
    }

    @Override
    public Page<UserNotification> getUserNotifications(User user, String type, Boolean isRead, Pageable pageable) {
        NotificationType notificationType = type != null ? NotificationType.valueOf(type.toUpperCase()) : null;
        return notificationRepository.findUserNotificationsWithFilters(user, notificationType, isRead, pageable);
    }

    @Override
    public void markAsRead(Long notificationId, User user) {
        int updated = notificationRepository.markAsRead(notificationId, user, LocalDateTime.now());
        if (updated > 0) {
            log.info("Notification {} marked as read for user: {}", notificationId, user.getEmail());
        }
    }

    @Override
    public void markAllAsRead(User user) {
        int updated = notificationRepository.markAllAsRead(user, LocalDateTime.now());
        log.info("Marked {} notifications as read for user: {}", updated, user.getEmail());
    }

    @Override
    public Long getUnreadCount(User user) {
        return notificationRepository.countByUserAndIsRead(user, false);
    }

    @Override
    public void deleteOldNotifications() {
        LocalDateTime cutoffDate = LocalDateTime.now().minusMonths(6);
        int deleted = notificationRepository.deleteOldNotifications(cutoffDate);
        log.info("Deleted {} old notifications", deleted);
    }
}
