package com.zenthrex.notification.services;

import com.zenthrex.core.entites.caravan.CaravanBooking;
import com.zenthrex.core.entites.crm.ProcurementOrder;

public interface SMSService {

    void sendBookingConfirmation(String phoneNumber, CaravanBooking booking);

    void sendBookingReminder(String phoneNumber, CaravanBooking booking);

    void sendSecurityCode(String phoneNumber, String code);

    void sendPaymentConfirmation(String phoneNumber, ProcurementOrder order);
}
