package com.example.RideSharingApplication.entities;


import com.example.RideSharingApplication.entities.enums.TransactionMethod;
import com.example.RideSharingApplication.entities.enums.TransactionType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class WalletTransaction {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;


    private double amount;

    private TransactionType transactionType;

    private TransactionMethod transactionMethod;

    @OneToOne
    private Ride ride;

    @ManyToOne
    private Wallet wallet;
    @CreationTimestamp
    private LocalDateTime timeStamp;
}
