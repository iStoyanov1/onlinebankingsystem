package com.example.onlinebankingsystem.domain.models.binding;

import java.util.Date;

public class FindTransactionByDate {

    Date dateFrom;
    Date dateTo;
    String transaction;

    public FindTransactionByDate() {
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }
}
