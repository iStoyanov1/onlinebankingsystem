package com.example.onlinebankingsystem.domain.models.services;

import com.example.onlinebankingsystem.domain.entities.User;
import com.example.onlinebankingsystem.domain.enums.AccountCurrency;

public class BankAccountServiceModel {

    private int id;
    private String accountNumber;
    private AccountCurrency currency;
    private double balance;
    private UserServiceModel user;


    public BankAccountServiceModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
