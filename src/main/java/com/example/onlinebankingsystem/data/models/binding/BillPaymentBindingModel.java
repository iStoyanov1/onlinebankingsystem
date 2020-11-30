package com.example.onlinebankingsystem.data.models.binding;

import com.example.onlinebankingsystem.data.entities.BankAccount;
import com.example.onlinebankingsystem.data.enums.BillType;
import com.example.onlinebankingsystem.data.models.services.base.BaseServiceModel;

import javax.validation.constraints.*;
import java.sql.Date;

public class BillPaymentBindingModel {

    private BillType type;
    private String supplier;
    private Double quantity;
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

    @NotNull(message = "Моля изберете доставчик")
    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    @Pattern(regexp = "^[0-9]{8,20}", message = "Клиентският номер трябва да съдържа между 8 и 20 символа")
    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    @NotNull(message = "Моля въведете дължима сума")
    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    @NotNull(message = "Моля изберете сметка")
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
