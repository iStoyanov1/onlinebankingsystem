package com.example.onlinebankingsystem.domain.entities;

import com.example.onlinebankingsystem.domain.base.BaseEntity;
import com.example.onlinebankingsystem.domain.enums.AccountCurrency;

import javax.persistence.*;


@Entity(name = "bank_account")
public class BankAccount extends BaseEntity {

    private String cardNumber;
    private AccountCurrency currency;
    private double balance;
    private User user;

    public BankAccount() {
    }

    @Column(name = "card_number")
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
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
