package com.zenthrex.core.repositories;

import com.zenthrex.core.entites.caravan.Caravan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaravanRepository extends JpaRepository<Caravan, Integer> {
}