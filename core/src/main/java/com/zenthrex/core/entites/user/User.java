package com.zenthrex.core.entites.user;

import com.zenthrex.core.entites.Token;
import com.zenthrex.core.enums.RoleEnum;
import com.zenthrex.core.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status = UserStatus.ACTIVE;

    private String avatarUrl;

    // Additional profile fields
    private String dateOfBirth;
    private String address;
    private String city;
    private String country;

    // Business information (for Pro users)
    private String businessName;
    private String businessType;
    private String taxId;
    private String businessAddress;
    private String businessPhone;
    private String website;

    // Verification fields
    private Boolean profileVerified = false;
    private LocalDateTime verifiedAt;
    private Integer verifiedBy;
    private String verificationNotes;

    @CreationTimestamp
    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @UpdateTimestamp
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    private LocalDateTime lastLoginAt;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Token> tokens;
}