package com.zenthrex.core.repositories;

import com.zenthrex.core.entites.crm.ProcurementOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcurementOrderRepository extends JpaRepository<ProcurementOrder, Long> {
}