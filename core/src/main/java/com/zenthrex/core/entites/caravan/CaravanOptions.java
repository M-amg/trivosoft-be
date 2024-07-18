package com.zenthrex.core.entites.caravan;

import com.zenthrex.core.entites.Options;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cr_options")
public class CaravanOptions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String entity;
    private Long entityId;

    @ManyToOne
    @JoinColumn(name = "caravan_id")
    private Caravan caravan;

    @ManyToOne
    @JoinColumn(name = "options_id")
    private Options options;
}
