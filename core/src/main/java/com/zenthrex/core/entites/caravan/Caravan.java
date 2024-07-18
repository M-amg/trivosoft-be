package com.zenthrex.core.entites.caravan;

import com.zenthrex.core.enums.CaravanCategory;
import com.zenthrex.core.enums.CaravanStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Caravan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;
    private String vin;
    @Enumerated(EnumType.STRING)
    private CaravanCategory category;
    private String brand;
    private String model;
    private Integer year;
    private Double height;
    private Double weight;
    private Boolean canMove;
    private Integer numberBed;
    private Double originalPrice;
    @Enumerated(EnumType.STRING)
    private CaravanStatus status;
    private Double latitude;
    private Double longitude;
    private Boolean isImmediatelyBooked;
    private Double assuranceAmount;
}
