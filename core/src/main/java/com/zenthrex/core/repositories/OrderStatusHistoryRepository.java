package com.zenthrex.core.repositories;

import com.zenthrex.core.entites.crm.Order;
import com.zenthrex.core.entites.crm.OrderStatusHistory;
import com.zenthrex.core.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderStatusHistoryRepository extends JpaRepository<OrderStatusHistory, Long> {

    List<OrderStatusHistory> findByOrderOrderByChangedAtDesc(Order order);

    List<OrderStatusHistory> findByOrderAndToStatus(Order order, OrderStatus status);

    @Query("SELECT osh FROM OrderStatusHistory osh WHERE osh.order = :order ORDER BY osh.changedAt ASC")
    List<OrderStatusHistory> findByOrderOrderByChangedAtAsc(@Param("order") Order order);

    @Query("""
            SELECT osh FROM OrderStatusHistory osh 
            WHERE osh.toStatus = :status 
            AND osh.changedAt BETWEEN :startDate AND :endDate
            ORDER BY osh.changedAt DESC
            """)
    List<OrderStatusHistory> findByStatusAndDateRange(
            @Param("status") OrderStatus status,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    Optional<OrderStatusHistory> findFirstByOrderOrderByChangedAtDesc(Order order);

    @Query("SELECT COUNT(osh) FROM OrderStatusHistory osh WHERE osh.order = :order")
    Long countByOrder(@Param("order") Order order);

    void deleteByOrder(Order order);
}
