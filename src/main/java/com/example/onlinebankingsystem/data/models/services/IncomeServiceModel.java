package com.example.onlinebankingsystem.data.models.services;

import com.example.onlinebankingsystem.data.models.services.base.BaseServiceModel;

import java.sql.Date;

public class IncomeServiceModel extends BaseServiceModel {

    private Date date;
    private double quantity;
    private String details;
    private String sender;
    private BankAccountServiceModel recipient;

    public IncomeServiceModel() {
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public BankAccountServiceModel getRecipient() {
        return recipient;
    }

    public void setRecipient(BankAccountServiceModel recipient) {
        this.recipient = recipient;
    }
}
