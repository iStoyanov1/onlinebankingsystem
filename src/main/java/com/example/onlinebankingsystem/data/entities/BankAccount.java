package com.example.onlinebankingsystem.data.entities;

import com.example.onlinebankingsystem.data.base.BaseEntity;
import com.example.onlinebankingsystem.data.enums.AccountCurrency;

import javax.persistence.*;


@Entity(name = "bank_account")
public class BankAccount extends BaseEntity {

    private String accountNumber;
    private AccountCurrency currency;
    private double balance;
    private User user;

    public BankAccount() {
    }

    @Column(name = "account_number")
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Column(name = "balance")
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    public AccountCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(AccountCurrency currency) {
        this.currency = currency;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
