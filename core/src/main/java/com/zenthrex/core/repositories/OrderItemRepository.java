package com.zenthrex.core.repositories;

import com.zenthrex.core.entites.crm.Order;
import com.zenthrex.core.entites.crm.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findByOrderOrderBySortOrderAsc(Order order);

    List<OrderItem> findByItemTypeAndItemId(String itemType, Long itemId);

    @Query("SELECT oi FROM OrderItem oi WHERE oi.order = :order ORDER BY oi.createdAt ASC")
    List<OrderItem> findByOrderOrderByCreatedAtAsc(@Param("order") Order order);

    @Query("SELECT COUNT(oi) FROM OrderItem oi WHERE oi.order = :order")
    Long countByOrder(@Param("order") Order order);

    @Query("SELECT SUM(oi.lineTotal) FROM OrderItem oi WHERE oi.order = :order")
    BigDecimal getTotalByOrder(@Param("order") Order order);

    void deleteByOrder(Order order);
}
