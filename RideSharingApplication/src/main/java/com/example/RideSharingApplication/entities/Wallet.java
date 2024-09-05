package com.example.RideSharingApplication.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primary key

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    // One-to-one mapping with the `User` entity. Each user has a unique wallet.
    private User user;

    private double balance = 0.0;  // Balance in the wallet

    @OneToMany(mappedBy = "wallet", fetch = FetchType.LAZY)
    // One wallet can have multiple transactions. The "wallet" attribute in `WalletTransaction` entity establishes the foreign key.
    private List<WalletTransaction> transactions;


}
