package com.example.RideSharingApplication.services.implementations;

import com.example.RideSharingApplication.entities.Ride;
import com.example.RideSharingApplication.entities.User;
import com.example.RideSharingApplication.entities.Wallet;
import com.example.RideSharingApplication.entities.WalletTransaction;
import com.example.RideSharingApplication.entities.enums.TransactionMethod;
import com.example.RideSharingApplication.entities.enums.TransactionType;
import com.example.RideSharingApplication.exceptions.ResourceNotFoundException;
import com.example.RideSharingApplication.repositories.WalletRepository;
import com.example.RideSharingApplication.services.WalletService;
import com.example.RideSharingApplication.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final ModelMapper modelMapper;
    private final WalletTransactionService walletTransactionService;
    @Override
    @Transactional
    public Wallet addMoneyToWallet(User user, double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet = findByUser(user);

        wallet.setBalance(wallet.getBalance() + amount);


        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transactionType(TransactionType.CREDIT)
                .transactionMethod(transactionMethod)
                .amount(amount)
                .build();
        walletTransactionService.createNewWalletTransaction(walletTransaction);

        return walletRepository.save(wallet);
    }

    @Override
    @Transactional
    public Wallet deductMoneyFromWallet(User user, double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet = findByUser(user);

        wallet.setBalance(wallet.getBalance() - amount);
        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transactionType(TransactionType.DEBIT)
                .transactionMethod(transactionMethod)
                .amount(amount)
                .build();
//        walletTransactionService.createNewWalletTransaction(walletTransaction);

        wallet.getTransactions().add(walletTransaction);
        return walletRepository.save(wallet);
    }


    @Override
    public void withdrawAllMyMoneyFromWallet() {

    }

    @Override
    public Wallet findWalletById(Long walletId) {
        return walletRepository.findById(walletId).orElseThrow(() -> new ResourceNotFoundException("Wallet not found with id"+ walletId)
        );
    }

    @Override
    public Wallet createNewWallet(User user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        return walletRepository.save(wallet);

    }

    @Override
    public Wallet findByUser(User user) {
        return walletRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found for user with id"+ user.getId())
                );

    }
}
