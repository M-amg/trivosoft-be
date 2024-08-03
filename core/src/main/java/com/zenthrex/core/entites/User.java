package com.zenthrex.core.entites;

import com.zenthrex.core.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private String password;
    private String status;

    @CreationTimestamp
    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;
    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

}
