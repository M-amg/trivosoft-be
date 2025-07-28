package com.zenthrex.caravan.controller;

import com.zenthrex.caravan.controller.api.CaravanApi;
import com.zenthrex.core.dtos.CaravanDto;
import com.zenthrex.caravan.services.CaravanService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CaravanController implements CaravanApi {
    private final CaravanService caravanService;

    @Override
    public ResponseEntity<Void> createCaravan(CaravanDto caravanDto) {
        caravanService.save(caravanDto);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CaravanDto> getCaravan(Integer id) {
        return  ResponseEntity.ok(caravanService.findById(id));
    }

    @Override
    public ResponseEntity<List<CaravanDto>> getCaravans() {
        return ResponseEntity.ok(caravanService.findAll());
    }

    @Override
    public ResponseEntity<Void> removeCaravan(Integer id) {
        caravanService.delete(id);
        return  new ResponseEntity<>(HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Void> updateCaravan(Integer id, CaravanDto caravanDto) {
        caravanService.update(id, caravanDto);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

}
