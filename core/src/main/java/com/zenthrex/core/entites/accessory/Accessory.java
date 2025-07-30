package com.zenthrex.core.entites.accessory;

import com.zenthrex.core.entites.User;
import com.zenthrex.core.enums.AccessoryCategory;
import com.zenthrex.core.enums.AccessoryStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "accessories")
public class Accessory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Double price;

    private Double deliveryPrice;

    @Enumerated(EnumType.STRING)
    private AccessoryCategory category;

    @Enumerated(EnumType.STRING)
    private AccessoryStatus status;

    @Column(nullable = false)
    private Integer stockQuantity;

    private String brand;
    private String model;
    private String sku; // Stock Keeping Unit
    private Double weight;
    private String dimensions;
    private String color;
    private String material;

    // Location coordinates for pickup/delivery
    private Double latitude;
    private Double longitude;

    @CreationTimestamp
    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @UpdateTimestamp
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User seller;

    @OneToMany(mappedBy = "accessory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccessoryImage> images;

    @OneToMany(mappedBy = "accessory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccessoryReview> reviews;
}