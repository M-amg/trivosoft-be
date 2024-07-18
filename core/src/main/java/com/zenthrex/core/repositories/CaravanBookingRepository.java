package com.zenthrex.core.repositories;

import com.zenthrex.core.entites.caravan.CaravanBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaravanBookingRepository extends JpaRepository<CaravanBooking, Long> {
}