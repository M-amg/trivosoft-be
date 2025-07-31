// core/src/main/java/com/zenthrex/core/repositories/AccessoryOrderRepository.java
package com.zenthrex.core.repositories;

import com.zenthrex.core.entites.accessory.AccessoryOrder;
import com.zenthrex.core.entites.user.User;
import com.zenthrex.core.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AccessoryOrderRepository extends JpaRepository<AccessoryOrder, Long> {

    Optional<AccessoryOrder> findByOrderNumber(String orderNumber);

    Page<AccessoryOrder> findByCustomerOrderByCreatedOnDesc(User customer, Pageable pageable);

    Page<AccessoryOrder> findByStatusOrderByCreatedOnDesc(OrderStatus status, Pageable pageable);

    @Query("""
        SELECT ao FROM AccessoryOrder ao 
        WHERE (:status IS NULL OR ao.status = :status)
        AND (:customerId IS NULL OR ao.customer.id = :customerId)
        AND (:startDate IS NULL OR ao.createdOn >= :startDate)
        AND (:endDate IS NULL OR ao.createdOn <= :endDate)
        ORDER BY ao.createdOn DESC
        """)
    Page<AccessoryOrder> findOrdersWithFilters(
            @Param("status") OrderStatus status,
            @Param("customerId") Integer customerId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable
    );

    Long countByCustomer(User customer);

    Long countByStatus(OrderStatus status);

    @Query("SELECT SUM(ao.totalAmount) FROM AccessoryOrder ao WHERE ao.customer = :customer AND ao.status = 'COMPLETED'")
    Double getTotalSpentByCustomer(@Param("customer") User customer);
}