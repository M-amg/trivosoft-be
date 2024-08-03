package com.zenthrex.caravan.mappers.resolver;

import com.zenthrex.caravan.mappers.CaravanMapper;
import com.zenthrex.core.entites.caravan.Caravan;
import com.zenthrex.core.repositories.CaravanRepository;
import com.zenthrex.trivo.dto.CaravanDto;
import lombok.RequiredArgsConstructor;
/*import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;*/
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CaravanMapperResolver {

private final CaravanRepository caravanRepository;
 /*   @ObjectFactory
    public Caravan resolve(CaravanDto dto, @TargetType Class<Caravan> type) {
        if (dto != null ) {
            Optional<Caravan> optionalCaravan = caravanRepository.findById(dto.getId());
            if (optionalCaravan.isPresent()) {
                return optionalCaravan.get();
            }
        }

        return new Caravan();
    }*/
}
