package com.zenthrex.core.repositories;

import com.zenthrex.core.entites.notification.NotificationHistory;
import com.zenthrex.core.entites.user.User;
import com.zenthrex.core.enums.NotificationStatus;
import com.zenthrex.core.enums.NotificationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationHistoryRepository extends JpaRepository<NotificationHistory, Long> {

    Page<NotificationHistory> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);

    Page<NotificationHistory> findByStatusOrderByCreatedAtDesc(NotificationStatus status, Pageable pageable);

    @Query("""

            SELECT nh FROM NotificationHistory nh 
        WHERE (:user IS NULL OR nh.user = :user)
        AND (:channel IS NULL OR nh.channel = :channel)
        AND (:status IS NULL OR nh.status = :status)
        AND (:notificationType IS NULL OR nh.notificationType = :notificationType)
        AND (:startDate IS NULL OR nh.createdAt >= :startDate)
        AND (:endDate IS NULL OR nh.createdAt <= :endDate)
        ORDER BY nh.createdAt DESC
        """)
    Page<NotificationHistory> findNotificationsWithFilters(
            @Param("user") User user,
            @Param("channel") String channel,
            @Param("status") NotificationStatus status,
            @Param("notificationType") NotificationType notificationType,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable
    );

    Long countByUserAndStatus(User user, NotificationStatus status);

    List<NotificationHistory> findByStatusAndCreatedAtBefore(NotificationStatus status, LocalDateTime cutoff);

    @Modifying
    @Query("DELETE FROM NotificationHistory nh WHERE nh.createdAt < :cutoffDate")
    int deleteOldNotifications(@Param("cutoffDate") LocalDateTime cutoffDate);

    @Query("""
        SELECT nh.channel, COUNT(nh) FROM NotificationHistory nh 
        WHERE nh.createdAt >= :startDate 
        GROUP BY nh.channel
        """)
    List<Object[]> getNotificationStatsByChannel(@Param("startDate") LocalDateTime startDate);
}
