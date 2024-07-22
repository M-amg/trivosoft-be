package com.zenthrex.caravan.controller;

import com.zenthrex.caravan.services.CaravanService;
import com.zenthrex.trivo.api.CaravansApi;
import com.zenthrex.trivo.dto.CaravanDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CaravanController implements CaravansApi {
    private final CaravanService caravanService;

    @Override
    public ResponseEntity<Void> createCaravan(CaravanDto caravanDto) {
        caravanService.save(caravanDto);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

}
