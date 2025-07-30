package com.zenthrex.accessory.services;

import com.zenthrex.core.dtos.AccessoryDto;
import com.zenthrex.core.entites.accessory.Accessory;
import com.zenthrex.core.repositories.AccessoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccessoryValidationService {

    private final AccessoryRepository accessoryRepository;

    public void validateNewAccessory(AccessoryDto accessoryDto) {
        validateRequiredFields(accessoryDto);
        validateBusinessRules(accessoryDto);
        validateUniqueness(accessoryDto);
    }

    public void validateAccessoryUpdate(AccessoryDto accessoryDto, Accessory existing) {
        validateRequiredFields(accessoryDto);
        validateBusinessRules(accessoryDto);

        // Check SKU uniqueness only if it's being changed
        if (accessoryDto.getSku() != null && !accessoryDto.getSku().equals(existing.getSku())) {
            validateSkuUniqueness(accessoryDto.getSku());
        }
    }

    private void validateRequiredFields(AccessoryDto accessoryDto) {
        if (accessoryDto.getTitle() == null || accessoryDto.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title is required");
        }

        if (accessoryDto.getPrice() == null || accessoryDto.getPrice() <= 0) {
            throw new IllegalArgumentException("Valid price is required");
        }

        if (accessoryDto.getCategory() == null) {
            throw new IllegalArgumentException("Category is required");
        }

        if (accessoryDto.getStockQuantity() == null || accessoryDto.getStockQuantity() < 0) {
            throw new IllegalArgumentException("Valid stock quantity is required");
        }
    }

    private void validateBusinessRules(AccessoryDto accessoryDto) {
        // Price validation
        if (accessoryDto.getPrice() != null && accessoryDto.getPrice() > 50000) {
            throw new IllegalArgumentException("Price cannot exceed €50,000");
        }

        // Delivery price validation
        if (accessoryDto.getDeliveryPrice() != null && accessoryDto.getDeliveryPrice() < 0) {
            throw new IllegalArgumentException("Delivery price cannot be negative");
        }

        // Weight validation
        if (accessoryDto.getWeight() != null && accessoryDto.getWeight() <= 0) {
            throw new IllegalArgumentException("Weight must be positive");
        }

        // Stock quantity validation
        if (accessoryDto.getStockQuantity() != null && accessoryDto.getStockQuantity() > 10000) {
            throw new IllegalArgumentException("Stock quantity seems unusually high");
        }
    }

    private void validateUniqueness(AccessoryDto accessoryDto) {
        if (accessoryDto.getSku() != null && !accessoryDto.getSku().trim().isEmpty()) {
            validateSkuUniqueness(accessoryDto.getSku());
        }
    }

    private void validateSkuUniqueness(String sku) {
        if (accessoryRepository.existsBySku(sku)) {
            throw new IllegalArgumentException("SKU already exists: " + sku);
        }
    }
}
