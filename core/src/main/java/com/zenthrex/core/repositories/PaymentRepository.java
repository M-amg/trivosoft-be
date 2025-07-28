package com.zenthrex.core.repositories;

import com.zenthrex.core.entites.crm.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}