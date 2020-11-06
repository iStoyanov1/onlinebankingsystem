package com.example.onlinebankingsystem.services;

import com.example.onlinebankingsystem.domain.models.services.BankAccountServiceModel;
import com.example.onlinebankingsystem.services.interfaces.BankAccountService;
import com.example.onlinebankingsystem.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class BankAccountServiceIml implements BankAccountService {

    private final ModelMapper modelMapper;
    private final UserService userService;

    public BankAccountServiceIml(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public BankAccountServiceModel saveBankAccount(BankAccountServiceModel bankAccountServiceModel) {
        return null;
    }
}
