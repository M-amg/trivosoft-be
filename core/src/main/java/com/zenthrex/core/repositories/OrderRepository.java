package com.zenthrex.core.repositories;

import com.zenthrex.core.entites.crm.Order;
import com.zenthrex.core.entites.user.User;
import com.zenthrex.core.enums.OrderStatus;
import com.zenthrex.core.enums.OrderType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByOrderNumber(String orderNumber);

    Page<Order> findByCustomerOrderByCreatedAtDesc(User customer, Pageable pageable);

    Page<Order> findByStatusOrderByCreatedAtDesc(OrderStatus status, Pageable pageable);

    List<Order> findByCustomerAndStatus(User customer, OrderStatus status);

    @Query("""
            SELECT o FROM Order o 
            WHERE (:orderType IS NULL OR o.orderType = :orderType)
            AND (:status IS NULL OR o.status = :status)
            AND (:customerId IS NULL OR o.customer.id = :customerId)
            AND (:startDate IS NULL OR o.createdAt >= :startDate)
            AND (:endDate IS NULL OR o.createdAt <= :endDate)
            ORDER BY o.createdAt DESC
            """)
    Page<Order> findOrdersWithFilters(
            @Param("orderType") OrderType orderType,
            @Param("status") OrderStatus status,
            @Param("customerId") Long customerId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable
    );

    @Query("SELECT COUNT(o) FROM Order o WHERE o.customer = :customer")
    Long countByCustomer(@Param("customer") User customer);

    @Query("SELECT COUNT(o) FROM Order o WHERE o.status = :status")
    Long countByStatus(@Param("status") OrderStatus status);

    @Query("SELECT SUM(o.totalAmount) FROM Order o WHERE o.customer = :customer AND o.status = 'COMPLETED'")
    java.math.BigDecimal getTotalSpentByCustomer(@Param("customer") User customer);

    @Query("""
            SELECT o FROM Order o 
            WHERE o.status IN ('PENDING', 'CONFIRMED') 
            AND o.createdAt < :cutoffDate
            ORDER BY o.createdAt ASC
            """)
    List<Order> findStaleOrders(@Param("cutoffDate") LocalDateTime cutoffDate);

    boolean existsByOrderNumber(String orderNumber);
}
