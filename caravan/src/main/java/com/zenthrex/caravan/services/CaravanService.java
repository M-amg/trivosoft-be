package com.zenthrex.caravan.services;

import com.zenthrex.caravan.dtos.AvailableCaravanDto;
import com.zenthrex.core.dtos.CaravanDto;
import com.zenthrex.caravan.helpers.CaravanValidationHelper;
import com.zenthrex.caravan.mappers.CaravanMapper;
import com.zenthrex.core.entites.user.User;
import com.zenthrex.core.entites.caravan.Caravan;
import com.zenthrex.core.entites.caravan.CaravanPricing;
import com.zenthrex.core.exception.ResourceNotFoundException;
import com.zenthrex.core.repositories.CaravanRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.zenthrex.core.enums.RoleEnum.SELLER;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CaravanService {


    private final CaravanRepository caravanRepository;
    private final CaravanMapper caravanMapper;
    private final CaravanValidationHelper caravanValidationHelper;

    public void save(CaravanDto caravanDto) {
        caravanValidationHelper.validateNewCaravan(caravanDto, caravanRepository);
        Caravan caravan = caravanMapper.toEntity(caravanDto);
        caravan.getCaravanPrices().forEach(price -> price.setCaravan(caravan));
        caravan.getStopSells().forEach(stopSell -> stopSell.setCaravan(caravan));
        caravan.getCancellationPolicies().forEach(policy -> policy.setCaravan(caravan));
        log.info("Saving caravan: {}", caravan);
        caravanRepository.save(caravan);
    }

    public void delete(Integer id) {
        Caravan caravan = caravanRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(" caravan not found"));
        caravanValidationHelper.validateCaravanUser(caravan.getUser());
        caravanRepository.deleteById(id);
    }

    public List<CaravanDto> findAll() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication();
        var caravanDtoList = caravanRepository.findAll().stream().map(caravanMapper::toDto);
        if (user != null && user.getRole() == SELLER) {
            caravanDtoList = caravanDtoList.filter(c -> Objects.equals(c.getUser().getId(), user.getId()));
        }
        return caravanDtoList.toList();
    }

    public CaravanDto findById(Integer id) {
        Caravan caravan = caravanRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(" caravan not found"));
        caravanValidationHelper.validateCaravanUser(caravan.getUser());
        return caravanMapper.toDto(caravan);
    }

    public void update(Integer id, CaravanDto caravanDto) {
        var caravan = caravanRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(" caravan not found"));
        caravanValidationHelper.validateCaravanUser(caravan.getUser());
        caravanDto.setId(id);
        caravanRepository.save(caravanMapper.toEntity(caravanDto));
    }

    public List<AvailableCaravanDto> findAvailableCaravansBetween(LocalDate startDate, LocalDate endDate) {
        List<Caravan> candidates = caravanRepository.findAvailableCaravansBetween(startDate, endDate);
        List<AvailableCaravanDto> results = new ArrayList<>();

        for (Caravan caravan : candidates) {
            double totalPrice = calculateTotalPrice(caravan, startDate, endDate);
            results.add(AvailableCaravanDto.builder()
                    .id(caravan.getId())
                    .title(caravan.getTitle())
                    .brand(caravan.getBrand())
                    .model(caravan.getModel())
                    .numberBed(caravan.getNumberBed())
                    .totalPrice(totalPrice)
                    .build());
        }

        return results;
    }
    private double calculateTotalPrice(Caravan caravan, LocalDate start, LocalDate end) {
        long totalDays = ChronoUnit.DAYS.between(start, end) + 1;
        double total = 0.0;

        List<CaravanPricing> prices = caravan.getCaravanPrices();
        Optional<CaravanPricing> pricing = prices.stream()
                .filter(p -> p.getDays().equals((int) totalDays))
                .findFirst();

        if (pricing.isPresent()) {
            return pricing.get().getPrice();
        } else {
            // Fallback: use defaultPrice per day
            double defaultPerDay = prices.stream()
                    .mapToDouble(CaravanPricing::getDefaultPrice)
                    .average()
                    .orElse(100.0); // fallback default

            return totalDays * defaultPerDay;
        }
    }

}
