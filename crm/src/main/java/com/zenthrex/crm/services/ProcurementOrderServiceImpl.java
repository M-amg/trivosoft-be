package com.zenthrex.crm.services;

import com.zenthrex.core.entites.caravan.CaravanBooking;
import com.zenthrex.core.entites.crm.ProcurementOrder;
import com.zenthrex.core.entites.crm.ProcurementOrderLine;
import com.zenthrex.core.enums.OrderStatus;
import com.zenthrex.core.repositories.ProcurementOrderLineRepository;
import com.zenthrex.core.repositories.ProcurementOrderRepository;
import com.zenthrex.crm.dto.ProcurementOrderDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcurementOrderServiceImpl implements ProcurementOrderService {

    private final ProcurementOrderRepository orderRepo;
    private final ProcurementOrderLineRepository lineRepo;

    @Override
    public void createOrderFromBooking(CaravanBooking booking) {
        ProcurementOrder order = ProcurementOrder.builder()
                .number("BOOK-" + booking.getId())
                .total(booking.getPriceUnite())
                .vat(booking.getAssurancePrice() * 0.2) // example logic
                .margin(booking.getMargin())
                .status(OrderStatus.PENDING)
                .build();

        ProcurementOrderLine line = ProcurementOrderLine.builder()
                .designation("Caravan Booking for: " + booking.getPickUp())
                .quantity(1)
                .unitPrice(booking.getPriceUnite())
                .vat(booking.getAssurancePrice())
                .margin(booking.getMargin())
                .entity("CaravanBooking")
                .entityId(booking.getId())
                .status("BOOKED")
                .procurementOrder(order)
                .build();

        order.setProcurementOrderLines(List.of(line));
        ProcurementOrder saved = orderRepo.save(order);
        ProcurementOrderDto.builder().id(saved.getId()).number(saved.getNumber()).build();
    }

    @Override
    public void updateOrderFromBooking(CaravanBooking booking) {
        ProcurementOrder order = orderRepo
                .findByNumber("BOOK-" + booking.getId())
                .orElseThrow(() -> new EntityNotFoundException("ProcurementOrder not found"));

        order.setTotal(booking.getPriceUnite());
        order.setVat(booking.getAssurancePrice() * 0.2); // update logic
        order.setMargin(booking.getMargin());

        // Update line(s) — assuming only one line per booking
        ProcurementOrderLine line = order.getProcurementOrderLines().get(0);
        line.setUnitPrice(booking.getPriceUnite());
        line.setVat(booking.getAssurancePrice());
        line.setMargin(booking.getMargin());
        line.setDesignation("Caravan Booking for: " + booking.getPickUp());

        orderRepo.save(order);
    }

}

