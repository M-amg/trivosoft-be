package com.zenthrex.core.repositories;

import com.zenthrex.core.entites.crm.ProcurementOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcurementOrderLineRepository extends JpaRepository<ProcurementOrderLine, Long> {
}