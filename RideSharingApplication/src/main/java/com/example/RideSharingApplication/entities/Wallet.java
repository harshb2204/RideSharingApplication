package com.example.RideSharingApplication.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primary key

    @OneToOne(fetch = FetchType.LAZY)
    // One-to-one mapping with the `User` entity. Each user has a unique wallet.
    private User user;

    private double balance;  // Balance in the wallet

    @OneToMany(mappedBy = "wallet", fetch = FetchType.LAZY)
    // One wallet can have multiple transactions. The "wallet" attribute in `WalletTransaction` entity establishes the foreign key.
    private List<WalletTransaction> transactions;
}
