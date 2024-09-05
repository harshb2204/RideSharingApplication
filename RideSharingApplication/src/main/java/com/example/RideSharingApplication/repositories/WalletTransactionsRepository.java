package com.example.RideSharingApplication.repositories;

import com.example.RideSharingApplication.entities.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WalletTransactionsRepository extends JpaRepository<WalletTransaction, Long> {
}
