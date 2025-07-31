package com.zenthrex.core.repositories;

import com.zenthrex.core.entites.user.NotificationSettings;
import com.zenthrex.core.entites.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationSettingsRepository extends JpaRepository<NotificationSettings, Long> {

    Optional<NotificationSettings> findByUser(User user);

    boolean existsByUser(User user);
}
