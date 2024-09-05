package com.example.RideSharingApplication.strategies.implementations;


import com.example.RideSharingApplication.entities.Driver;
import com.example.RideSharingApplication.entities.Payment;
import com.example.RideSharingApplication.entities.Wallet;
import com.example.RideSharingApplication.entities.enums.PaymentStatus;
import com.example.RideSharingApplication.entities.enums.TransactionMethod;
import com.example.RideSharingApplication.repositories.PaymentRepository;
import com.example.RideSharingApplication.services.WalletService;
import com.example.RideSharingApplication.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


//Rider-> 100
//Driver-> 70 deduct 30rs
@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;
    @Override
    public void processPayment(Payment payment) {

        Driver driver = payment.getRide().getDriver();
        Wallet wallet = walletService.findByUser(driver.getUser());
        double platformCommision = payment.getAmount() * PLATFORM_COMMISION;

        walletService.deductMoneyFromWallet(driver.getUser(),  platformCommision, null, payment.getRide()
        , TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
