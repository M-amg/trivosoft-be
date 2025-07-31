package com.zenthrex.core.repositories;

import com.zenthrex.core.entites.accessory.Accessory;
import com.zenthrex.core.entites.accessory.AccessoryOrder;
import com.zenthrex.core.entites.accessory.AccessoryOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccessoryOrderItemRepository extends JpaRepository<AccessoryOrderItem, Long> {

    List<AccessoryOrderItem> findByOrder(AccessoryOrder order);

    List<AccessoryOrderItem> findByAccessory(Accessory accessory);

    @Query("SELECT COUNT(aoi) FROM AccessoryOrderItem aoi WHERE aoi.accessory = :accessory")
    Long countByAccessory(@Param("accessory") Accessory accessory);

    @Query("SELECT SUM(aoi.quantity) FROM AccessoryOrderItem aoi WHERE aoi.accessory = :accessory")
    Integer getTotalQuantityByAccessory(@Param("accessory") Accessory accessory);
}
