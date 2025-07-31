package com.zenthrex.core.entites.caravan;

import com.zenthrex.core.entites.Features;
import com.zenthrex.core.entites.user.User;
import com.zenthrex.core.enums.CaravanCategory;
import com.zenthrex.core.enums.CaravanStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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


    @OneToMany(mappedBy = "caravan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CaravanPricing> caravanPrices;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "caravan_features",
            joinColumns = @JoinColumn(name = "caravan_id"),
            inverseJoinColumns = @JoinColumn(name = "feature_id")
    )
    private List<Features> features;


    @OneToMany(mappedBy = "caravan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CaravanStopSelling> stopSells;


    @OneToMany(mappedBy = "caravan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CaravanCancellationPolicy> cancellationPolicies;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
