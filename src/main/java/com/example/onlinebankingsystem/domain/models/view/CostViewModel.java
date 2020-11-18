package com.example.onlinebankingsystem.domain.models.view;

import com.example.onlinebankingsystem.domain.entities.BankAccount;

import java.sql.Date;

public class CostViewModel {

    private Date date;
    private double quantity;
    private String details;
    private BankAccountViewModel sender;
    private String recipient;

    public CostViewModel() {
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

    public BankAccountViewModel getSender() {
        return sender;
    }

    public void setSender(BankAccountViewModel sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
}
