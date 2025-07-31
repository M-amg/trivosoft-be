package com.zenthrex.core.repositories;

import com.zenthrex.core.entites.user.PrivacySettings;
import com.zenthrex.core.entites.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrivacySettingsRepository extends JpaRepository<PrivacySettings, Long> {

    Optional<PrivacySettings> findByUser(User user);

    boolean existsByUser(User user);
}
