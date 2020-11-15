package com.example.onlinebankingsystem.services.interfaces;

import com.example.onlinebankingsystem.domain.models.services.BankAccountServiceModel;
import com.example.onlinebankingsystem.domain.models.services.UserServiceModel;

public interface BankAccountService {

    BankAccountServiceModel findBankAccountByUser(String username);

}
