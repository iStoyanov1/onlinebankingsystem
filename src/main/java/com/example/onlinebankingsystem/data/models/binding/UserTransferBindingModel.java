package com.example.onlinebankingsystem.data.models.binding;

import com.example.onlinebankingsystem.data.entities.BankAccount;

import javax.validation.constraints.*;
import java.util.Date;

public class UserTransferBindingModel {


    private String sender;
    private Double quantity;
    private String recipientName;
    private String recipient;

    public UserTransferBindingModel() {
    }


    @NotNull(message = "Моля изберете сметка")
    @NotEmpty(message = "Моля изберете сметка")
    public String getSender() {
        return sender;
    }


    public void setSender(String sender) {
        this.sender = sender;
    }

    @Min(value = 10, message = "Минималната сума за превод е 10лв")
    @NotNull(message = "Моля въведете сума")
    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    @NotNull
    @NotEmpty(message = "Моля въведете име на получателля")
    @Size(min = 3, message = "Името трябва да бъде между 3 и 30 символа")
    @Size(max = 30, message = "Името трябва да бъде между 3 и 30 символа")
    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    @NotNull
    @NotEmpty(message = "Моля въведете IBAN на получателля")
    @Pattern(regexp = "^(?:(?:IT|SM)\\d{2}[A-Z]\\d{22}|CY\\d{2}[A-Z]\\d{23}|NL\\d{2}[A-Z]{4}\\d{10}|LV\\d{2}[A-Z]{4}\\d{13}|(?:BG|BH|GB|IE)\\d{2}[A-Z]{4}\\d{14}|GI\\d{2}[A-Z]{4}\\d{15}|RO\\d{2}[A-Z]{4}\\d{16}|KW\\d{2}[A-Z]{4}\\d{22}|MT\\d{2}[A-Z]{4}\\d{23}|NO\\d{13}|(?:DK|FI|GL|FO)\\d{16}|MK\\d{17}|(?:AT|EE|KZ|LU|XK)\\d{18}|(?:BA|HR|LI|CH|CR)\\d{19}|(?:GE|DE|LT|ME|RS)\\d{20}|IL\\d{21}|(?:AD|CZ|ES|MD|SA)\\d{22}|PT\\d{23}|(?:BE|IS)\\d{24}|(?:FR|MR|MC)\\d{25}|(?:AL|DO|LB|PL)\\d{26}|(?:AZ|HU)\\d{27}|(?:GR|MU)\\d{28})$"
    , message = "Неправилен IBAN")
    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
}
