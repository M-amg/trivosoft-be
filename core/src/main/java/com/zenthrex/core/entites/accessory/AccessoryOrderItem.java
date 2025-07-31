package com.zenthrex.core.entites.accessory;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "accessory_order_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccessoryOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private AccessoryOrder order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accessory_id", nullable = false)
    private Accessory accessory;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;
}
