package com.example.RideSharingApplication.services;

import com.example.RideSharingApplication.entities.Payment;
import com.example.RideSharingApplication.entities.Ride;
import com.example.RideSharingApplication.entities.enums.PaymentStatus;

public interface PaymentService {

    void processPayment(Ride ride);

    Payment createNewPayment(Ride ride);

    void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus);
}
