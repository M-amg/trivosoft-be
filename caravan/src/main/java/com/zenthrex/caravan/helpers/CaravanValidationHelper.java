package com.zenthrex.caravan.helpers;


import com.zenthrex.core.dtos.CaravanDto;
import com.zenthrex.caravan.exceptions.CaravanValidationException;
import com.zenthrex.core.entites.user.User;
import com.zenthrex.core.enums.RoleEnum;
import com.zenthrex.core.repositories.CaravanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CaravanValidationHelper {

    protected final static long MAX_CARAVAN_PER_USER = 5;
    private final AuditorAware<Integer> auditorAware;

    public void validateNewCaravan(CaravanDto caravanDto, CaravanRepository caravanRepository) {
        if (caravanRepository.findByVin(caravanDto.getVin()).isPresent()) {
            throw new CaravanValidationException("VIN must be unique.");
        }

        if (caravanDto.getUser() != null && caravanRepository.countByUserId(caravanDto.getUser().getId()) >= MAX_CARAVAN_PER_USER) {
            throw new CaravanValidationException("User cannot have more than one caravan.");
        }
    }

    public void validateCaravanUser(User user) {
        Integer authId = auditorAware.getCurrentAuditor().orElse(null);
        if (user == null || (user.getRole() != RoleEnum.ADMIN && !user.getId().equals(authId))) {
            throw new CaravanValidationException("User is not authorized");
        }
    }
}