package com.example.onlinebankingsystem.data.models.binding;

import com.example.onlinebankingsystem.data.entities.BankAccount;
import com.example.onlinebankingsystem.data.enums.BillType;

import java.sql.Date;

public class BillPaymentBindingModel {

    private BillType type;
    private String supplier;
    private double quantity;
    private String clientNumber;
    private String account;

    public BillPaymentBindingModel() {
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

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
