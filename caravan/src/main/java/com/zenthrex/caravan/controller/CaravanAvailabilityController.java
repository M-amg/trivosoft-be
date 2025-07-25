package com.zenthrex.caravan.controller;

import com.zenthrex.caravan.controller.api.CaravanAvailabilityApi;
import com.zenthrex.caravan.dtos.AvailableCaravanDto;
import com.zenthrex.caravan.services.CaravanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CaravanAvailabilityController implements CaravanAvailabilityApi {

    private final CaravanService caravanService;

    @Override
    public List<AvailableCaravanDto> getAvailableCaravans(LocalDate startDate, LocalDate endDate) {
        return caravanService.findAvailableCaravansBetween(startDate, endDate);
    }
}

