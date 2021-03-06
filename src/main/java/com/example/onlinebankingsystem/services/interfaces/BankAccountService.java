package com.example.onlinebankingsystem.services.interfaces;

import com.example.onlinebankingsystem.data.models.services.BankAccountServiceModel;

public interface BankAccountService {

    BankAccountServiceModel findBankAccountByUser(String username);

    BankAccountServiceModel findBankAccountByUserId(int id);

    BankAccountServiceModel findBankAccByAccNumber(String acc);

    void reduceBalance(Double amount, BankAccountServiceModel bankAccountServiceModel);

    void incomeBalance(Double amount, BankAccountServiceModel bankAccountServiceModel);
}
