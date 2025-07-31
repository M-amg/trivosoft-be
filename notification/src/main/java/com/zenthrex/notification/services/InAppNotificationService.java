package com.zenthrex.notification.services;

import com.zenthrex.core.entites.user.User;
import com.zenthrex.core.entites.user.UserNotification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InAppNotificationService {

    void createNotification(User user, String title, String message, String type);

    void createNotification(User user, String title, String message, String type, String actionUrl);

    Page<UserNotification> getUserNotifications(User user, String type, Boolean isRead, Pageable pageable);

    void markAsRead(Long notificationId, User user);

    void markAllAsRead(User user);

    Long getUnreadCount(User user);

    void deleteOldNotifications();
}
