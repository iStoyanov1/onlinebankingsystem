package com.example.onlinebankingsystem.web.controllers;

import com.example.onlinebankingsystem.domain.models.services.BankAccountServiceModel;
import com.example.onlinebankingsystem.domain.models.services.CostServiceModel;
import com.example.onlinebankingsystem.domain.models.services.IncomeServiceModel;
import com.example.onlinebankingsystem.domain.models.services.UserServiceModel;
import com.example.onlinebankingsystem.services.interfaces.BankAccountService;
import com.example.onlinebankingsystem.services.interfaces.CostService;
import com.example.onlinebankingsystem.services.interfaces.IncomeService;
import com.example.onlinebankingsystem.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final BankAccountService bankAccountService;
    private final IncomeService incomeService;
    private final CostService costService;

    public UserController(UserService userService, ModelMapper modelMapper, BankAccountService bankAccountService, IncomeService incomeService, CostService costService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.bankAccountService = bankAccountService;
        this.incomeService = incomeService;
        this.costService = costService;
    }

    @GetMapping("/profile")
    public ModelAndView home(Principal principal, ModelAndView modelAndView){

        UserServiceModel user = this.userService.findUserByUsername(principal.getName());
        BankAccountServiceModel bankAccount = this.bankAccountService.findBankAccountByUser(user.getUsername());
        List<IncomeServiceModel> userIncomes = this.incomeService.findAllIncomesByUser(bankAccount.getUser().getUsername());
        List<CostServiceModel> userCosts = this.costService.userCosts(bankAccount.getUser().getUsername());
        modelAndView.addObject("userCosts", userCosts);
        modelAndView.addObject("userIncomes", userIncomes);
        modelAndView.addObject("user", bankAccount);
        modelAndView.setViewName("/user/profile");
        return modelAndView;
    }


}
