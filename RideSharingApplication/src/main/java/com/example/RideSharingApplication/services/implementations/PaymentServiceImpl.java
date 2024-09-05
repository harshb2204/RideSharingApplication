package com.example.RideSharingApplication.services.implementations;

import com.example.RideSharingApplication.entities.Payment;
import com.example.RideSharingApplication.entities.Ride;
import com.example.RideSharingApplication.entities.enums.PaymentStatus;
import com.example.RideSharingApplication.exceptions.ResourceNotFoundException;
import com.example.RideSharingApplication.repositories.PaymentRepository;
import com.example.RideSharingApplication.services.PaymentService;
import com.example.RideSharingApplication.strategies.PaymentStrategyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentStrategyManager paymentStrategyManager;
    @Override
    public void processPayment(Ride ride) {
        Payment payment = paymentRepository.findByRide(ride).orElseThrow(() ->
                new ResourceNotFoundException("Payment not found for ride:"+ ride.getId()));
        paymentStrategyManager.paymentStrategy(payment.getPaymentMethod()).processPayment(payment);

    }

    @Override
    public Payment createNewPayment(Ride ride) {

        Payment payment = Payment.builder().ride(ride).paymentMethod(ride.getPaymentMethod())
                .amount(ride.getFare())
                .paymentStatus(PaymentStatus.PENDING)
                .build();

        return paymentRepository.save(payment);
    }

    @Override
    public void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus) {
        payment.setPaymentStatus(paymentStatus);
        paymentRepository.save(payment);
    }
}
