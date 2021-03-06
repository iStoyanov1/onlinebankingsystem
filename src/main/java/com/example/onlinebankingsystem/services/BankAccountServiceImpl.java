package com.example.onlinebankingsystem.services;

import com.example.onlinebankingsystem.data.entities.BankAccount;
import com.example.onlinebankingsystem.data.models.services.BankAccountServiceModel;
import com.example.onlinebankingsystem.repositories.BankAccountRepository;
import com.example.onlinebankingsystem.services.interfaces.BankAccountService;
import com.example.onlinebankingsystem.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class BankAccountServiceImpl implements BankAccountService {


    private final ModelMapper modelMapper;
    private final UserService userService;
    private final BankAccountRepository bankAccountRepository;

    public BankAccountServiceImpl(ModelMapper modelMapper, UserService userService, BankAccountRepository bankAccountRepository) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.bankAccountRepository = bankAccountRepository;
    }


    @Override
    public BankAccountServiceModel findBankAccountByUser(String username) {
        return this.modelMapper.map(this.bankAccountRepository.findBankAccountByUser_Username(username),
                BankAccountServiceModel.class);
    }

    @Override
    public BankAccountServiceModel findBankAccountByUserId(int id) {
        return this.modelMapper.map(this.bankAccountRepository
                .findBankAccountByUser_Id(id), BankAccountServiceModel.class);
    }

    @Override
    public BankAccountServiceModel findBankAccByAccNumber(String acc) {
        return this.modelMapper.map(this.bankAccountRepository.findBankAccountByAccountNumber(acc),
                            BankAccountServiceModel.class);
    }

    @Override
    public void reduceBalance(Double amount, BankAccountServiceModel bankAccountServiceModel) {
        BankAccount bankAccount = this.bankAccountRepository.findBankAccountByAccountNumber(bankAccountServiceModel.getAccountNumber());
        bankAccount.setBalance(bankAccount.getBalance() - amount);

    }

    @Override
    public void incomeBalance(Double amount, BankAccountServiceModel bankAccountServiceModel) {
        BankAccount bankAccount = this.bankAccountRepository.findBankAccountByAccountNumber(bankAccountServiceModel.getAccountNumber());
        bankAccount.setBalance(bankAccount.getBalance() + amount);
    }
}
