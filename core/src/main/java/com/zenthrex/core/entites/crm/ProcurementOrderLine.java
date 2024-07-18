package com.zenthrex.core.entites.crm;

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
@Table(name = "command_lines")
public class ProcurementOrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String designation;
    private Integer quantity;
    private Double unitPrice;
    private Double vat;
    private Double margin;
    private String entity;
    private Long entityId;
    private String status;

    @ManyToOne
    @JoinColumn(name = "command_id")
    private ProcurementOrder command;

    @CreationTimestamp
    @Column(name = "created_on")
    private LocalDateTime createdOn;
}
