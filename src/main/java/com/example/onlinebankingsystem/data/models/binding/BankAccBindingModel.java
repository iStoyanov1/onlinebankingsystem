package com.example.onlinebankingsystem.data.models.binding;

import com.example.onlinebankingsystem.data.entities.User;
import com.example.onlinebankingsystem.data.enums.AccountCurrency;

public class BankAccBindingModel {

    private String accountNumber;
    private AccountCurrency currency;
    private double balance;
    private String user;

    public BankAccBindingModel() {
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
