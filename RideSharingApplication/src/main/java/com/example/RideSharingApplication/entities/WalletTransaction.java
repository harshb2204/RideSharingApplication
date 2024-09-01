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
    private Long id;  // Primary key

    private double amount;  // Transaction amount

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;  // Enum for the type of transaction (CREDIT, DEBIT)

    @Enumerated(EnumType.STRING)
    private TransactionMethod transactionMethod;  // Enum for the method (BANKING, RIDE)

    @OneToOne
    // One-to-one mapping with the `Ride` entity. Each transaction can be linked to one ride.
    private Ride ride;

    @ManyToOne
    // Many transactions can belong to one wallet.
    private Wallet wallet;

    @CreationTimestamp
    private LocalDateTime timeStamp;  // Timestamp of the transaction
}
