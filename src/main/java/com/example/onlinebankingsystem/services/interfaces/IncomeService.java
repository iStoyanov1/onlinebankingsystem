package com.example.onlinebankingsystem.services.interfaces;

import com.example.onlinebankingsystem.data.models.services.BankAccountServiceModel;
import com.example.onlinebankingsystem.data.models.services.IncomeServiceModel;

import java.sql.Date;
import java.util.List;

public interface IncomeService {

    List<IncomeServiceModel> findLastIncomesByUser(String username);

    List<IncomeServiceModel> findAllIncomesByUser(String username);

    List<IncomeServiceModel> userIncomeBetweenDates(String username, Date from, Date to);

    void setIncome(IncomeServiceModel income, BankAccountServiceModel recipient, String details);



}
