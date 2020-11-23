package com.example.onlinebankingsystem.services.interfaces;

import com.example.onlinebankingsystem.domain.models.services.CostServiceModel;

import java.sql.Date;
import java.util.List;

public interface CostService {

    List<CostServiceModel> userLastCosts(String username);

    List<CostServiceModel> userAllCosts(String username);

    List<CostServiceModel> userCostsBetweenDates(String username, Date from, Date to);

}
