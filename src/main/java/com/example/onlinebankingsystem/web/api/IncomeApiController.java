package com.example.onlinebankingsystem.web.api;

import com.example.onlinebankingsystem.domain.models.view.IncomeViewModel;
import com.example.onlinebankingsystem.services.interfaces.IncomeService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class IncomeApiController {

    private final ModelMapper modelMapper;
    private final IncomeService incomeService;

    public IncomeApiController(ModelMapper modelMapper, IncomeService incomeService) {
        this.modelMapper = modelMapper;
        this.incomeService = incomeService;
    }

    @GetMapping(value = "/api/transaction/income")
    public List<IncomeViewModel> allIncomes(Principal principal){
        return this.incomeService.findAllIncomesByUser(principal.getName())
                .stream()
                .map(income -> this.modelMapper.map(income, IncomeViewModel.class))
                .collect(Collectors.toList());
    }

}
