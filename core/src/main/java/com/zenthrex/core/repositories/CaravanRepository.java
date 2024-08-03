package com.zenthrex.core.repositories;

import com.zenthrex.core.entites.caravan.Caravan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CaravanRepository extends JpaRepository<Caravan, Integer> {
    Optional<Caravan> findByVin(String vin);
    long countByUserId(Integer userId);
}