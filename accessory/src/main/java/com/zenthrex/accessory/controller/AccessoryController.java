package com.zenthrex.accessory.controller;

import com.zenthrex.accessory.controller.api.AccessoryApi;
import com.zenthrex.accessory.services.AccessoryService;
import com.zenthrex.core.dtos.AccessoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class AccessoryController implements AccessoryApi {

    private final AccessoryService accessoryService;

    @Override
    public ResponseEntity<AccessoryDto> createAccessory(AccessoryDto accessoryDto) {
        AccessoryDto created = accessoryService.createAccessory(accessoryDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AccessoryDto> getAccessory(Long id) {
        AccessoryDto accessory = accessoryService.findById(id);
        return ResponseEntity.ok(accessory);
    }

    @Override
    public ResponseEntity<Page<AccessoryDto>> getAccessories(
            String category, String status, String search,
            BigDecimal minPrice, BigDecimal maxPrice,
            String brand, Boolean inStock, Pageable pageable) {

        Page<AccessoryDto> accessories = accessoryService.findAllWithFilters(
                category, status, search, minPrice, maxPrice, brand, inStock, pageable);
        return ResponseEntity.ok(accessories);
    }

    @Override
    public ResponseEntity<Void> updateAccessory(Long id, AccessoryDto accessoryDto) {
        accessoryService.updateAccessory(id, accessoryDto);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> removeAccessory(Long id) {
        accessoryService.deleteAccessory(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Page<AccessoryDto>> searchAccessories(String query, Pageable pageable) {
        Page<AccessoryDto> results = accessoryService.searchAccessories(query, pageable);
        return ResponseEntity.ok(results);
    }

    @Override
    public ResponseEntity<Void> updateStock(Long id, Integer quantity) {
        accessoryService.updateStock(id, quantity);
        return ResponseEntity.ok().build();
    }
}

