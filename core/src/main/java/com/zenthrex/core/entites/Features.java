package com.zenthrex.core.entites;

import com.zenthrex.core.entites.caravan.Caravan;
import com.zenthrex.core.enums.FeaturesType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Features {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String icon;
    @Enumerated(EnumType.STRING)
    private FeaturesType type;
    @ElementCollection
    private List<String> entities;
    @ManyToMany(mappedBy = "features")
    private List<Caravan> caravans;
}
