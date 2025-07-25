package com.zenthrex.crm.services;

import com.zenthrex.core.entites.caravan.CaravanBooking;

public interface ProcurementOrderService {
    void createOrderFromBooking(CaravanBooking booking);
    void updateOrderFromBooking(CaravanBooking booking);

}
