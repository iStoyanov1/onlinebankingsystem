package com.example.onlinebankingsystem.data.entities;

import com.example.onlinebankingsystem.data.base.BaseEntity;
import com.example.onlinebankingsystem.data.enums.BillType;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "bills")
public class Bills extends BaseEntity {

   private BillType type;
   private String supplier;
   private Date date;
   private double quantity;
   private BankAccount account;

   public Bills() {
   }

   @Column(name = "type")
   public BillType getType() {
      return type;
   }

   public void setType(BillType type) {
      this.type = type;
   }

   @Column(name = "supplier")
   public String getSupplier() {
      return supplier;
   }

   public void setSupplier(String supplier) {
      this.supplier = supplier;
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

   @ManyToOne
   public BankAccount getAccount() {
      return account;
   }

   public void setAccount(BankAccount account) {
      this.account = account;
   }
}
