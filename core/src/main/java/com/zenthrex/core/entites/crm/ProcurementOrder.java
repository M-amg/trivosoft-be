package com.zenthrex.core.entites.crm;

import com.zenthrex.core.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "commands")
public class ProcurementOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;
    private Double total;
    private Double vat;
    private Double margin;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @CreationTimestamp
    @Column(name = "created_on")
    private LocalDateTime createdOn;
}
