package com.zenthrex.core.repositories;

import com.zenthrex.core.entites.caravan.Caravan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CaravanRepository extends JpaRepository<Caravan, Integer> {
    Optional<Caravan> findByVin(String vin);
    long countByUserId(Integer userId);
    @Query("""
    SELECT DISTINCT c FROM Caravan c
    WHERE c.status = com.zenthrex.core.enums.CaravanStatus.AVAILABLE
    AND c.id NOT IN (
        SELECT cb.caravan.id FROM CaravanBooking cb
        WHERE cb.startDate <= :endDate AND cb.endDate >= :startDate
    )
    AND c.id NOT IN (
        SELECT ss.caravan.id FROM CaravanStopSelling ss
        WHERE ss.day BETWEEN :startDate AND :endDate
    )
    """)
    List<Caravan> findAvailableCaravansBetween(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

}