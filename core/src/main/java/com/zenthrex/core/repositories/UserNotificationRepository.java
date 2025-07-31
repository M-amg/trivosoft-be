package com.zenthrex.core.repositories;

import com.zenthrex.core.entites.user.User;
import com.zenthrex.core.entites.user.UserNotification;
import com.zenthrex.core.enums.NotificationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface UserNotificationRepository extends JpaRepository<UserNotification, Long> {

    Page<UserNotification> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);

    @Query("""

            SELECT un FROM UserNotification un 
        WHERE un.user = :user 
        AND (:type IS NULL OR un.notificationType = :type)
        AND (:isRead IS NULL OR un.isRead = :isRead)
        ORDER BY un.createdAt DESC
        """)
    Page<UserNotification> findUserNotificationsWithFilters(
            @Param("user") User user,
            @Param("type") NotificationType type,
            @Param("isRead") Boolean isRead,
            Pageable pageable
    );

    @Modifying
    @Query("UPDATE UserNotification un SET un.isRead = true, un.readAt = :readAt WHERE un.id = :id AND un.user = :user")
    int markAsRead(@Param("id") Long id, @Param("user") User user, @Param("readAt") LocalDateTime readAt);

    @Modifying
    @Query("UPDATE UserNotification un SET un.isRead = true, un.readAt = :readAt WHERE un.user = :user AND un.isRead = false")
    int markAllAsRead(@Param("user") User user, @Param("readAt") LocalDateTime readAt);

    Long countByUserAndIsRead(User user, Boolean isRead);

    List<UserNotification> findTop5ByUserAndIsReadFalseOrderByCreatedAtDesc(User user);

    @Modifying
    @Query("DELETE FROM UserNotification un WHERE un.createdAt < :cutoffDate")
    int deleteOldNotifications(@Param("cutoffDate") LocalDateTime cutoffDate);
}
