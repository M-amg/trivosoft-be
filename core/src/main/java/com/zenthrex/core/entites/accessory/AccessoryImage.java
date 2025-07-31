// core/src/main/java/com/zenthrex/core/entites/accessory/AccessoryImage.java
package com.zenthrex.core.entites.accessory;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "accessory_images")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccessoryImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accessory_id", nullable = false)
    private Accessory accessory;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    private String title;

    @Column(name = "display_order", nullable = false)
    private Integer displayOrder = 0;

    @Column(name = "is_primary", nullable = false)
    private Boolean isPrimary = false;

    @CreationTimestamp
    @Column(name = "created_on")
    private LocalDateTime createdOn;
}
