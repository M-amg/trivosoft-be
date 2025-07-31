package com.zenthrex.core.repositories;

import com.zenthrex.core.entites.notification.NotificationTemplate;
import com.zenthrex.core.enums.NotificationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationTemplateRepository extends JpaRepository<NotificationTemplate, Long> {

    Optional<NotificationTemplate> findByTemplateKey(String templateKey);

    List<NotificationTemplate> findByNotificationTypeAndIsActiveTrue(NotificationType notificationType);

    Page<NotificationTemplate> findByIsActiveTrue(Pageable pageable);

    Page<NotificationTemplate> findByNotificationType(NotificationType notificationType, Pageable pageable);

    boolean existsByTemplateKey(String templateKey);
}
