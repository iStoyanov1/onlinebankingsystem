package com.example.onlinebankingsystem.web.api;

import com.example.onlinebankingsystem.domain.models.view.CostViewModel;
import com.example.onlinebankingsystem.services.interfaces.CostService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CostApiController {

    private final ModelMapper modelMapper;
    private final CostService costService;

    public CostApiController(ModelMapper modelMapper, CostService costService) {
        this.modelMapper = modelMapper;
        this.costService = costService;
    }

    @GetMapping(value = "/api/transaction/cost")
    public List<CostViewModel> costs(Principal principal){

        return this.costService.userAllCosts(principal.getName())
                .stream().map(cost -> this.modelMapper.map(cost, CostViewModel.class))
                .collect(Collectors.toList());

    }
}
