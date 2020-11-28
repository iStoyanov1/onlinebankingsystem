package com.example.onlinebankingsystem.services.interfaces;

import com.example.onlinebankingsystem.data.models.services.BankAccountServiceModel;

public interface BankAccountService {

    BankAccountServiceModel findBankAccountByUser(String username);

    BankAccountServiceModel findBankAccountByUserId(int id);

    BankAccountServiceModel findBankAccByAccNumber(String acc);

    void reduceBalance(double amount, BankAccountServiceModel bankAccountServiceModel);
}
