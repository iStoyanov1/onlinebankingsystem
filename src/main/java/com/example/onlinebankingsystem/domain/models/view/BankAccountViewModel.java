package com.example.onlinebankingsystem.domain.models.view;

public class BankAccountViewModel {

    private String accountNumber;
    private double balance;
    private UserViewModel user;

    public BankAccountViewModel() {

    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }


    public UserViewModel getUser() {
        return user;
    }

    public void setUser(UserViewModel user) {
        this.user = user;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
