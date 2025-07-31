package com.zenthrex.core.repositories;

import com.zenthrex.core.entites.User;
import com.zenthrex.core.enums.RoleEnum;
import com.zenthrex.core.enums.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    List<User> findAllByRole(RoleEnum roleEnum);

    boolean existsByEmail(String email);

    // Custom query for filtering users
    @Query("""
        SELECT u FROM User u 
        WHERE (:role IS NULL OR u.role = :role)
        AND (:status IS NULL OR u.status = :status)
        AND (:search IS NULL OR 
             LOWER(u.firstname) LIKE LOWER(CONCAT('%', :search, '%')) OR 
             LOWER(u.lastname) LIKE LOWER(CONCAT('%', :search, '%')) OR 
             LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%')))
        """)
    Page<User> findAllWithFilters(
            @Param("role") String role,
            @Param("status") String status,
            @Param("search") String search,
            Pageable pageable
    );

    // Find clients with filters (for agents)
    @Query("""
        SELECT u FROM User u 
        WHERE u.role IN ('BUYER', 'SELLER')
        AND (:clientType IS NULL OR 
             (u.role = 'SELLER' AND :clientType = 'PRO') OR 
             (u.role = 'BUYER' AND :clientType = 'STANDARD'))
        AND (:status IS NULL OR u.status = :status)
        AND (:search IS NULL OR 
             LOWER(u.firstname) LIKE LOWER(CONCAT('%', :search, '%')) OR 
             LOWER(u.lastname) LIKE LOWER(CONCAT('%', :search, '%')) OR 
             LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%')) OR 
             u.phone LIKE CONCAT('%', :search, '%'))
        AND (:verified IS NULL OR u.profileVerified = :verified)
        """)
    Page<User> findClientsWithFilters(
            @Param("clientType") String clientType,
            @Param("status") String status,
            @Param("search") String search,
            @Param("verified") Boolean verified,
            Pageable pageable
    );

    // Count users by role and status
    Long countByRoleAndStatus(RoleEnum role, UserStatus status);

    // Find users created between dates
    List<User> findByCreatedOnBetween(LocalDateTime start, LocalDateTime end);

    // Find users with pending verification
    @Query("SELECT u FROM User u WHERE u.profileVerified = false AND u.verifiedAt IS NULL")
    Page<User> findPendingVerifications(Pageable pageable);

    // Update last login
    @Query("UPDATE User u SET u.lastLoginAt = :loginTime WHERE u.id = :userId")
    void updateLastLogin(@Param("userId") Integer userId, @Param("loginTime") LocalDateTime loginTime);

    // Find users by status
    Page<User> findByStatus(UserStatus status, Pageable pageable);
}