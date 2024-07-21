package com.zenthrex.core.entites;

import com.zenthrex.core.entites.caravan.Caravan;
import com.zenthrex.core.entites.caravan.CaravanBooking;
import com.zenthrex.core.entites.crm.Payment;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;
    private String status;

    @CreationTimestamp
    @Column(name = "created_on")
    private LocalDateTime createdOn;

}
