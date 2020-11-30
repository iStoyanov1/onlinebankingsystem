package com.example.onlinebankingsystem.services.interfaces;

import com.example.onlinebankingsystem.data.models.services.CostServiceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.util.List;

public interface CostService {

    List<CostServiceModel> userLastCosts(String username);

    List<CostServiceModel> userAllCosts(String username, Pageable pageable);

    List<CostServiceModel> userCostsBetweenDates(String username, Date from, Date to);

    void setCost(CostServiceModel cost, String costDetails);

    Page<CostServiceModel> findCostsByPage(Pageable pageable, String username);

}
