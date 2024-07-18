package com.zenthrex.core.entites.caravan;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cr_pricing")
public class CaravanPricing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer days;
    private Double price;
    private Double defaultPrice;
    private Double weekEndSupp;
    private Double limitKM;
    private Double kmPricing;
}
