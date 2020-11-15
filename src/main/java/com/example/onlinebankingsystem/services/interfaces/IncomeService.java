package com.example.onlinebankingsystem.services.interfaces;

import com.example.onlinebankingsystem.domain.models.services.IncomeServiceModel;

import java.util.List;

public interface IncomeService {

    List<IncomeServiceModel> findAllIncomesByUser(String username);

}
