package com.zenthrex.core.repositories;

import com.zenthrex.core.entites.caravan.CaravanCancellationPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaravanCancellationPolicyRepository extends JpaRepository<CaravanCancellationPolicy, Integer> {
}