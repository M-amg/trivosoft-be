package com.zenthrex.caravan.controller.api;

import com.zenthrex.caravan.dtos.CaravanBookingDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/caravan-bookings")
public interface CaravanBookingApi {

    @PostMapping
    @Operation(summary = "Create a new caravan booking")
    CaravanBookingDto create(@RequestBody CaravanBookingDto dto);

    @GetMapping("/{id}")
    @Operation(summary = "Get a booking by ID")
    CaravanBookingDto getById(@PathVariable Long id);

    @GetMapping
    @Operation(summary = "Get all bookings")
    List<CaravanBookingDto> getAll();

    @PutMapping("/{id}")
    @Operation(summary = "Update a booking")
    CaravanBookingDto update(@PathVariable Long id, @RequestBody CaravanBookingDto dto);

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a booking")
    void delete(@PathVariable Long id);
}

