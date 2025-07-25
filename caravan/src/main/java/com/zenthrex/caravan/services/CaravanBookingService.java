package com.zenthrex.caravan.services;

import com.zenthrex.caravan.dtos.CaravanBookingDto;
import com.zenthrex.caravan.mappers.CaravanBookingMapper;
import com.zenthrex.core.entites.caravan.CaravanBooking;
import com.zenthrex.core.repositories.CaravanBookingRepository;
import com.zenthrex.crm.services.ProcurementOrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CaravanBookingService {

    private final CaravanBookingRepository repository;
    private final CaravanBookingMapper caravanBookingMapper;
    private final ProcurementOrderService procurementOrderService;

    public CaravanBookingDto save(CaravanBookingDto dto) {
        CaravanBooking booking = caravanBookingMapper.toEntity(dto);
        procurementOrderService.createOrderFromBooking(booking);

        return caravanBookingMapper.toDto(repository.save(booking));
    }

    public CaravanBookingDto findById(Long id) {
        return repository.findById(id)
                .map(caravanBookingMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found"));
    }

    public List<CaravanBookingDto> findAll() {
        return repository.findAll().stream()
                .map(caravanBookingMapper::toDto)
                .collect(Collectors.toList());
    }

    public CaravanBookingDto update(Long id, CaravanBookingDto dto) {
        CaravanBooking existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found"));

        CaravanBooking updated = caravanBookingMapper.toEntity(dto);
        updated.setId(existing.getId());
        return caravanBookingMapper.toDto(repository.save(updated));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
