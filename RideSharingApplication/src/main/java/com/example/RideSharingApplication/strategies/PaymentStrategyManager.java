package com.example.RideSharingApplication.strategies;


import com.example.RideSharingApplication.entities.enums.PaymentMethod;
import com.example.RideSharingApplication.strategies.implementations.CashPaymentStrategy;
import com.example.RideSharingApplication.strategies.implementations.WalletPaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {
    private final WalletPaymentStrategy walletPaymentStrategy;
    private final CashPaymentStrategy cashPaymentStrategy;

    public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod){
        return switch (paymentMethod){
            case WALLET -> walletPaymentStrategy;
            case CASH -> cashPaymentStrategy;

        };

    }
}
