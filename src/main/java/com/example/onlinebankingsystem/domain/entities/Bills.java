package com.example.onlinebankingsystem.domain.entities;

import com.example.onlinebankingsystem.domain.base.BaseEntity;
import com.example.onlinebankingsystem.domain.enums.BillStatus;

import javax.persistence.*;

@Entity(name = "bills")
public class Bills extends BaseEntity {

    private String name;
    private String type;
    private BillStatus status;
    private double amount;
    private User user;

    public Bills() {
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public BillStatus getStatus() {
        return status;
    }

    public void setStatus(BillStatus status) {
        this.status = status;
    }
    @Column(name = "amount")
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
