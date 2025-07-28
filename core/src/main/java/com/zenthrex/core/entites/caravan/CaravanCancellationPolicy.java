package com.zenthrex.core.entites.caravan;

import com.zenthrex.core.enums.PenaltyType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cr_cancellation_policy")
public class CaravanCancellationPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;
    private Integer daysBefore;
    private Double penalty;
    @Enumerated(EnumType.STRING)
    private PenaltyType penaltyType;

    @ManyToOne
    @JoinColumn(name = "caravan_id", nullable = false)
    private Caravan caravan;
}
