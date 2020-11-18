package com.example.onlinebankingsystem.domain.models.view;

import java.sql.Date;

public class IncomeViewModel {

    private Date date;
    private double quantity;
    private String details;
    private String sender;
    private BankAccountViewModel recipient;

    public IncomeViewModel() {
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

    public BankAccountViewModel getRecipient() {
        return recipient;
    }

    public void setRecipient(BankAccountViewModel recipient) {
        this.recipient = recipient;
    }
}
