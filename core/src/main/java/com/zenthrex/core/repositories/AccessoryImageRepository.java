package com.zenthrex.core.repositories;

import com.zenthrex.core.entites.accessory.Accessory;
import com.zenthrex.core.entites.accessory.AccessoryImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccessoryImageRepository extends JpaRepository<AccessoryImage, Long> {

    List<AccessoryImage> findByAccessoryOrderByDisplayOrderAsc(Accessory accessory);

    Optional<AccessoryImage> findByAccessoryAndIsPrimaryTrue(Accessory accessory);

    @Modifying
    @Query("UPDATE AccessoryImage ai SET ai.isPrimary = false WHERE ai.accessory = :accessory")
    void clearPrimaryImages(@Param("accessory") Accessory accessory);

    Long countByAccessory(Accessory accessory);

    void deleteByAccessory(Accessory accessory);
}
