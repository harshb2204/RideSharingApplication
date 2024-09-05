package com.example.RideSharingApplication.services;

import com.example.RideSharingApplication.entities.Ride;
import com.example.RideSharingApplication.entities.User;
import com.example.RideSharingApplication.entities.Wallet;
import com.example.RideSharingApplication.entities.enums.TransactionMethod;

public interface WalletService {

    Wallet addMoneyToWallet(User user, double amount, String transactionId, Ride ride, TransactionMethod transactionMethod);

    Wallet deductMoneyFromWallet(User user, double amount, String transactionId, Ride ride, TransactionMethod transactionMethod);

    void withdrawAllMyMoneyFromWallet();

    Wallet findWalletById(Long walletId);

    Wallet createNewWallet(User user);

    Wallet findByUser(User user);
}
