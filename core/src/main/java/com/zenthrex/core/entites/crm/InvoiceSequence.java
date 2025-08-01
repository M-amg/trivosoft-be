package com.zenthrex.core.entites.crm;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "invoice_sequences")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceSequence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sequence_name", nullable = false, unique = true)
    private String sequenceName;

    @Column(name = "prefix")
    private String prefix;

    @Column(name = "current_number", nullable = false)
    private Long currentNumber;

    @Column(name = "increment_by")
    private Integer incrementBy;

    @Column(name = "padding_length")
    private Integer paddingLength;

    @Column(name = "reset_yearly")
    private Boolean resetYearly;

    @Column(name = "last_reset_year")
    private Integer lastResetYear;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
