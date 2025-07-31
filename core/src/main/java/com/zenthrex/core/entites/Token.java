package com.zenthrex.core.entites;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zenthrex.core.entites.user.PrivacySettings;
import com.zenthrex.core.entites.user.User;
import com.zenthrex.core.enums.TokenType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @Column(unique = true)
    public String token;
    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;
    public boolean revoked;
    public boolean expired;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    public User user;

    public static interface PrivacySettingsRepository extends JpaRepository<PrivacySettings, Long> {

        Optional<PrivacySettings> findByUser(User user);

        boolean existsByUser(User user);
    }
}
