package com.zenthrex.caravan.services;

import com.zenthrex.caravan.helpers.CaravanValidationHelper;
import com.zenthrex.caravan.mappers.CaravanMapper;
import com.zenthrex.core.repositories.CaravanRepository;
import com.zenthrex.trivo.dto.CaravanDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CaravanService {
    private final CaravanRepository caravanRepository;
    private final CaravanMapper caravanMapper;

    public void save(CaravanDto caravanDto) {
        CaravanValidationHelper.validateNewCaravan(caravanDto,caravanRepository);
        caravanRepository.save(caravanMapper.toEntity(caravanDto));
    }
}
