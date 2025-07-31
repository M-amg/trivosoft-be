package com.zenthrex.core.repositories;

import com.zenthrex.core.entites.user.ProfileVerification;
import com.zenthrex.core.entites.user.User;
import com.zenthrex.core.enums.VerificationStatus;
import com.zenthrex.core.enums.VerificationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProfileVerificationRepository extends JpaRepository<ProfileVerification, Long> {

    Page<ProfileVerification> findByStatusOrderBySubmittedAtDesc(VerificationStatus status, Pageable pageable);

    List<ProfileVerification> findByUserOrderBySubmittedAtDesc(User user);

    Optional<ProfileVerification> findByUserAndVerificationTypeAndStatus(
            User user, VerificationType verificationType, VerificationStatus status);

    @Query("SELECT pv FROM ProfileVerification pv WHERE pv.status = 'PENDING' ORDER BY pv.submittedAt ASC")
    Page<ProfileVerification> findPendingVerifications(Pageable pageable);

    boolean existsByUserAndVerificationTypeAndStatusIn(
            User user, VerificationType verificationType, List<VerificationStatus> statuses);
}
