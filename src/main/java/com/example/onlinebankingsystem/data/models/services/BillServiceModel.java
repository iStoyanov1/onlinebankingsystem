package com.example.onlinebankingsystem.data.models.services;

import com.example.onlinebankingsystem.data.entities.BankAccount;
import com.example.onlinebankingsystem.data.enums.BillType;
import com.example.onlinebankingsystem.data.models.services.base.BaseServiceModel;

import java.sql.Date;

public class BillServiceModel extends BaseServiceModel {

    private BillType type;
    private String supplier;
    private Date date;
    private double quantity;
    private BankAccountServiceModel account;

    public BillServiceModel() {
    }

    public BillType getType() {
        return type;
    }

    public void setType(BillType type) {
        this.type = type;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public BankAccountServiceModel getAccount() {
        return account;
    }

    public void setAccount(BankAccountServiceModel account) {
        this.account = account;
    }
}
