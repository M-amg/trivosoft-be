package com.zenthrex.core.entites.caravan;

import com.zenthrex.core.entites.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cr_booking")
public class CaravanBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;
    private LocalDate endDate;
    private Double priceUnite;
    private Double assurancePrice;
    private String pickUp;
    private Double margin;
    private String status;
    @CreationTimestamp
    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caravan_id", nullable = false)
    private Caravan caravan;


}
