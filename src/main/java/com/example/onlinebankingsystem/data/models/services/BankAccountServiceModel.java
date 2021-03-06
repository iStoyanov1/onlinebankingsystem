package com.example.onlinebankingsystem.data.models.services;

import com.example.onlinebankingsystem.data.enums.AccountCurrency;
import com.example.onlinebankingsystem.data.models.services.base.BaseServiceModel;

public class BankAccountServiceModel extends BaseServiceModel {

    private String accountNumber;
    private AccountCurrency currency;
    private double balance;
    private UserServiceModel user;


    public BankAccountServiceModel() {

    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(AccountCurrency currency) {
        this.currency = currency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public UserServiceModel getUser() {
        return user;
    }

    public void setUser(UserServiceModel user) {
        this.user = user;
    }
}
