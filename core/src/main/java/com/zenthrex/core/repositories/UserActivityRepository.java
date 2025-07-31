// core/src/main/java/com/zenthrex/core/repositories/UserActivityRepository.java
package com.zenthrex.core.repositories;

import com.zenthrex.core.entites.user.User;
import com.zenthrex.core.entites.user.UserActivity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {

    Page<UserActivity> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);

    Page<UserActivity> findByUserAndActivityTypeOrderByCreatedAtDesc(User user, String activityType, Pageable pageable);

    @Query("""
        SELECT ua FROM UserActivity ua 
        WHERE ua.user = :user 
        AND (:activityType IS NULL OR ua.activityType = :activityType)
        AND (:fromDate IS NULL OR ua.createdAt >= :fromDate)
        AND (:toDate IS NULL OR ua.createdAt <= :toDate)
        ORDER BY ua.createdAt DESC
        """)
    Page<UserActivity> findUserActivitiesWithFilters(
            @Param("user") User user,
            @Param("activityType") String activityType,
            @Param("fromDate") LocalDateTime fromDate,
            @Param("toDate") LocalDateTime toDate,
            Pageable pageable
    );

    List<UserActivity> findTop10ByUserOrderByCreatedAtDesc(User user);

    Long countByUserAndCreatedAtAfter(User user, LocalDateTime since);
}
