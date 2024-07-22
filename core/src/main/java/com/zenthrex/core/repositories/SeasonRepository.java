package com.zenthrex.core.repositories;

import com.zenthrex.core.entites.Season;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonRepository extends JpaRepository<Season, Long> {
}