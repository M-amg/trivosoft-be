package com.zenthrex.core.entites.user;

import com.zenthrex.core.enums.DataRetentionPolicy;
import com.zenthrex.core.enums.ProfileVisibility;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "privacy_settings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrivacySettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "profile_visibility", nullable = false)
    private ProfileVisibility profileVisibility = ProfileVisibility.PUBLIC;

    @Column(name = "show_email", nullable = false)
    private Boolean showEmail = false;

    @Column(name = "show_phone", nullable = false)
    private Boolean showPhone = false;

    @Column(name = "allow_marketing", nullable = false)
    private Boolean allowMarketing = true;

    @Column(name = "allow_analytics", nullable = false)
    private Boolean allowAnalytics = true;

    @Enumerated(EnumType.STRING)
    @Column(name = "data_retention", nullable = false)
    private DataRetentionPolicy dataRetention = DataRetentionPolicy.STANDARD;

    @Column(name = "third_party_sharing", nullable = false)
    private Boolean thirdPartySharing = false;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
