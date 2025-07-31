package com.zenthrex.core.entites.crm;


import com.zenthrex.core.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private Double amount;
    private String operationNumber;
    private Double assurancePrice;
    private String name;
    private Double margin;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    @ManyToOne
    @JoinColumn(name = "procurement_order_id", nullable = false)
    private ProcurementOrder procurementOrder;

}
