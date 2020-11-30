package com.example.onlinebankingsystem.web.api.contollers;

import com.example.onlinebankingsystem.data.entities.Cost;
import com.example.onlinebankingsystem.data.models.services.CostServiceModel;
import com.example.onlinebankingsystem.repositories.CostRepository;
import com.example.onlinebankingsystem.web.api.models.CostResponseModel;
import com.example.onlinebankingsystem.services.interfaces.CostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CostApiController {

    private final ModelMapper modelMapper;
    private final CostService costService;
    private final CostRepository costRepository;

    public CostApiController(ModelMapper modelMapper, CostService costService, CostRepository costRepository) {
        this.modelMapper = modelMapper;
        this.costService = costService;
        this.costRepository = costRepository;
    }

    @GetMapping(value = "/api/transaction/cost")
    public List<CostResponseModel> costs(Principal principal, Pageable pageable){


        return this.costService.userAllCosts(principal.getName(), pageable)
                .stream().map(cost -> this.modelMapper.map(cost, CostResponseModel.class))
                .collect(Collectors.toList());

    }
    @PostMapping(value = "/api/transaction/cost")
    public void costsBetweenDates(Principal principal, @RequestParam("dateFrom") String dateFrom,
                                                     @RequestParam("dateTo") String dateTo, HttpServletResponse response) throws ParseException, IOException {

        Date convertDateFrom = new SimpleDateFormat("yyyy-MM-dd").parse(dateFrom);
        Date convertDateTo = new SimpleDateFormat("yyyy-MM-dd").parse(dateTo);

        java.sql.Date fromDate = new java.sql.Date(convertDateFrom.getTime());
        java.sql.Date toDate = new java.sql.Date(convertDateTo.getTime());

        this.costService.userCostsBetweenDates(principal.getName(), fromDate, toDate)
                .stream()
                .map(cost -> this.modelMapper.map(cost, CostResponseModel.class))
                .collect(Collectors.toList());

        response.sendRedirect("redirect:/user/profile/transactions");

    }
}
