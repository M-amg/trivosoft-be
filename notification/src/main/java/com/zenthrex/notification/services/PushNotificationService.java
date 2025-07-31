package com.zenthrex.notification.services;

public interface PushNotificationService {

    void sendToUser(Integer userId, String title, String message);

    void sendToRole(String role, String title, String message);

    void sendOrderNotification(Integer userId, String orderNumber, String status);

    void sendBookingReminder(Integer userId, String bookingDetails);
}
