package com.example.RideSharingApplication.dto;

import com.example.RideSharingApplication.entities.enums.TransactionMethod;
import com.example.RideSharingApplication.entities.enums.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class WalletTransactionDto {

    private Long id;

    private double amount;


    private TransactionType transactionType;


    private TransactionMethod transactionMethod;

    private String transactionId;



    private RideDto ride;



    private WalletDto wallet;


    private LocalDateTime timeStamp;
}

