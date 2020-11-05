package com.example.onlinebankingsystem.domain.services;

import com.example.onlinebankingsystem.domain.entities.BankAccount;
import com.example.onlinebankingsystem.domain.entities.Bills;

import java.util.List;

public class UserServiceModel {

    private int id;
    private String email;
    private String password;
    private String phone;
    private String country;
    private String city;
    private List<BankAccountServiceModel> bankAccount;
    private List<BillsServiceModel> bills;

    public UserServiceModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<BankAccountServiceModel> getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(List<BankAccountServiceModel> bankAccount) {
        this.bankAccount = bankAccount;
    }

    public List<BillsServiceModel> getBills() {
        return bills;
    }

    public void setBills(List<BillsServiceModel> bills) {
        this.bills = bills;
    }
}
