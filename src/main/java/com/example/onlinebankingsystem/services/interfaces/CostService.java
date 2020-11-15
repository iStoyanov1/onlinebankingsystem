package com.example.onlinebankingsystem.services.interfaces;

import com.example.onlinebankingsystem.domain.models.services.CostServiceModel;

import java.util.List;

public interface CostService {

    List<CostServiceModel> userCosts(String username);

}
