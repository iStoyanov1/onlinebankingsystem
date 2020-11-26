package com.example.onlinebankingsystem.data.models.services;

import java.sql.Date;

public class CostServiceModel {

    private Date date;
    private double quantity;
    private String details;
    private BankAccountServiceModel sender;
    private String recipient;

    public CostServiceModel() {
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

    public BankAccountServiceModel getSender() {
        return sender;
    }

    public void setSender(BankAccountServiceModel sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
}
