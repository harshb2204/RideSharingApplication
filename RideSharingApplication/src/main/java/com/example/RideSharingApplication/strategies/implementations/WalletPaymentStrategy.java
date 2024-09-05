package com.example.RideSharingApplication.strategies.implementations;

import com.example.RideSharingApplication.entities.Driver;
import com.example.RideSharingApplication.entities.Payment;
import com.example.RideSharingApplication.entities.Rider;
import com.example.RideSharingApplication.entities.enums.PaymentStatus;
import com.example.RideSharingApplication.entities.enums.TransactionMethod;
import com.example.RideSharingApplication.repositories.PaymentRepository;
import com.example.RideSharingApplication.services.WalletService;
import com.example.RideSharingApplication.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//Ride->232rs, Driver has 500
//Ride cost->100rs, commission = 30
//Rider -> 232-100 = 132
//Driver -> 500 + (100-30) = 570rs
@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;
    @Override
    @Transactional
    public void processPayment(Payment payment) {

        Driver driver = payment.getRide().getDriver();
        Rider rider = payment.getRide().getRider();

        walletService.deductMoneyFromWallet(rider.getUser(), payment.getAmount(), null, payment.getRide()
        , TransactionMethod.RIDE);

        double driversProfit = payment.getAmount() * (1-PLATFORM_COMMISION);

        walletService.addMoneyToWallet(driver.getUser(), driversProfit, null, payment.getRide(), TransactionMethod.RIDE);


        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
