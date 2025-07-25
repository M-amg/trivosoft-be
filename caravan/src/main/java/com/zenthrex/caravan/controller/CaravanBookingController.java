package com.zenthrex.caravan.controller;

import com.zenthrex.caravan.controller.api.CaravanBookingApi;
import com.zenthrex.caravan.dtos.CaravanBookingDto;
import com.zenthrex.caravan.services.CaravanBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CaravanBookingController implements CaravanBookingApi {

    private final CaravanBookingService service;

    @Override
    public CaravanBookingDto create(@RequestBody CaravanBookingDto dto) {
        return service.save(dto);
    }

    @Override
    public CaravanBookingDto getById(Long id) {
        return service.findById(id);
    }

    @Override
    public List<CaravanBookingDto> getAll() {
        return service.findAll();
    }

    @Override
    public CaravanBookingDto update(Long id, @RequestBody CaravanBookingDto dto) {
        return service.update(id, dto);
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }
}
