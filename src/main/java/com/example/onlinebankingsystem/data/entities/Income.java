package com.example.onlinebankingsystem.data.entities;

import com.example.onlinebankingsystem.data.base.BaseEntity;


import javax.persistence.*;
import java.sql.Date;

@Entity(name = "income")
public class Income extends BaseEntity {


    private Date date;
    private double quantity;
    private String details;
    private String sender;
    private BankAccount recipient;

    public Income() {
    }


    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "quantity")
    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Column(name = "details")
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Column(name = "sender")
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @OneToOne
    public BankAccount getRecipient() {
        return recipient;
    }

    public void setRecipient(BankAccount recipient) {
        this.recipient = recipient;
    }
}
