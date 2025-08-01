package com.zenthrex.crm.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderNumberGenerator {

    private static final String ORDER_PREFIX = "ORD";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");

    /**
     * Generate unique order number
     * Format: ORD-YYYYMMDD-XXXXXX (e.g., ORD-20250801-123456)
     */
    @Transactional
    public String generateOrderNumber() {
        String datePart = LocalDateTime.now().format(DATE_FORMAT);
        String randomPart = String.format("%06d", ThreadLocalRandom.current().nextInt(999999));

        String orderNumber = String.format("%s-%s-%s", ORDER_PREFIX, datePart, randomPart);

        log.debug("Generated order number: {}", orderNumber);
        return orderNumber;
    }

    /**
     * Generate order number with custom prefix
     */
    public String generateOrderNumber(String customPrefix) {
        String datePart = LocalDateTime.now().format(DATE_FORMAT);
        String randomPart = String.format("%06d", ThreadLocalRandom.current().nextInt(999999));

        String orderNumber = String.format("%s-%s-%s", customPrefix, datePart, randomPart);

        log.debug("Generated custom order number: {}", orderNumber);
        return orderNumber;
    }
}


