package com.zenthrex.caravan.helpers;


import com.zenthrex.caravan.exceptions.ValidationCaravanException;
import com.zenthrex.core.repositories.CaravanRepository;
import com.zenthrex.trivo.dto.CaravanDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CaravanValidationHelper {

    protected final static long MAX_CARAVAN_PER_USER = 5;

    public static void validateNewCaravan(CaravanDto caravanDto, CaravanRepository caravanRepository) {
        if (caravanRepository.findByVin(caravanDto.getVin()).isPresent()) {
            throw new ValidationCaravanException("VIN must be unique.");
        }

        if (caravanDto.getUser() != null && caravanRepository.countByUserId(caravanDto.getUser().getId()) >= MAX_CARAVAN_PER_USER) {
            throw new ValidationCaravanException("User cannot have more than one caravan.");
        }
    }
}