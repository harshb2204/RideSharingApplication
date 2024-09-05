package com.example.RideSharingApplication.strategies;

import com.example.RideSharingApplication.entities.Payment;

public interface PaymentStrategy {

     Double PLATFORM_COMMISION = 0.3;

    void processPayment(Payment payment);
}
