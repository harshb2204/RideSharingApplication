package com.example.RideSharingApplication.services.implementations;

import com.example.RideSharingApplication.entities.WalletTransaction;
import com.example.RideSharingApplication.repositories.WalletTransactionsRepository;
import com.example.RideSharingApplication.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {

    private final WalletTransactionsRepository walletTransactionsRepository;

    private final ModelMapper modelMapper;
    @Override
    public void createNewWalletTransaction(WalletTransaction walletTransaction) {

        walletTransactionsRepository.save(walletTransaction);


    }
}
