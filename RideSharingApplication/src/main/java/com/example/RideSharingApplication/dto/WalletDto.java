package com.example.RideSharingApplication.dto;

import com.example.RideSharingApplication.entities.User;
import lombok.Data;

import java.util.List;

@Data
public class WalletDto {

    private Long id;



    private User user;

    private double balance;


    private List<WalletTransactionDto> transactions;
}
