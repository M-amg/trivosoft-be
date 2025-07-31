package com.zenthrex.core.repositories;

import com.zenthrex.core.entites.user.ProUpgradeRequest;
import com.zenthrex.core.entites.user.User;
import com.zenthrex.core.enums.RequestStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProUpgradeRequestRepository extends JpaRepository<ProUpgradeRequest, Long> {

    Page<ProUpgradeRequest> findByStatusOrderByCreatedAtDesc(RequestStatus status, Pageable pageable);

    Page<ProUpgradeRequest> findAllByOrderByCreatedAtDesc(Pageable pageable);

    Optional<ProUpgradeRequest> findByUserAndStatus(User user, RequestStatus status);

    Optional<ProUpgradeRequest> findTopByUserOrderByCreatedAtDesc(User user);

    @Query("""

            SELECT pur FROM ProUpgradeRequest pur 
        WHERE (:status IS NULL OR pur.status = :status)
        ORDER BY pur.createdAt DESC
        """)
    Page<ProUpgradeRequest> findRequestsWithFilters(@Param("status") RequestStatus status, Pageable pageable);

    boolean existsByUserAndStatusIn(User user, java.util.List<RequestStatus> statuses);

    Long countByStatus(RequestStatus status);
}
