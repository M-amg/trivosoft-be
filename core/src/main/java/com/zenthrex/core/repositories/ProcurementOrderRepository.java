package com.zenthrex.core.repositories;

import com.zenthrex.core.entites.crm.ProcurementOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProcurementOrderRepository extends JpaRepository<ProcurementOrder, Long> {
    Optional<ProcurementOrder> findByNumber(String number);
}