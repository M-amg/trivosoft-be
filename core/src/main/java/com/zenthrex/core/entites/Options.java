package com.zenthrex.core.entites;

import com.zenthrex.core.enums.OptionType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Options {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Enumerated(EnumType.STRING)
    private OptionType type;
    @ElementCollection
    private List<String> entities;
}
