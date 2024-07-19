package com.zenthrex.core.entites.caravan;

import com.zenthrex.core.enums.PenalityType;
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
    private Double penality;
    @Enumerated(EnumType.STRING)
    private PenalityType penalityType;

    @ManyToOne
    @JoinColumn(name = "caravan_id", nullable = false)
    private Caravan caravan;
}
