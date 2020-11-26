package com.example.onlinebankingsystem.web.view.controllers.usercontroler;

import com.example.onlinebankingsystem.services.interfaces.BankAccountService;
import com.example.onlinebankingsystem.services.interfaces.CostService;
import com.example.onlinebankingsystem.services.interfaces.IncomeService;
import com.example.onlinebankingsystem.web.view.models.BankAccountViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class TransactionsController {


    private final ModelMapper modelMapper;
    private final BankAccountService bankAccountService;
    private final CostService costService;
    private final IncomeService incomeService;

    public TransactionsController(ModelMapper modelMapper, BankAccountService bankAccountService, CostService costService, IncomeService incomeService) {
        this.modelMapper = modelMapper;
        this.bankAccountService = bankAccountService;
        this.costService = costService;
        this.incomeService = incomeService;
    }


    @GetMapping("/profile/transactions")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView transactionView(ModelAndView modelAndView, Principal principal) {

       /* List<CostResponseModel> costs = this.costService.userAllCosts(principal.getName())
                .stream().map(cost -> this.modelMapper.map(cost, CostResponseModel.class))
                .collect(Collectors.toList());
        String userEmail = null;
        String userFullName = null;
        String userAcc = null;
        for (CostResponseModel cost : costs) {
            userEmail = cost.getSender().getUser().getEmail();
            userFullName = cost.getSender().getUser().getFullName();
            userAcc = cost.getSender().getAccountNumber();
        }
        modelAndView.addObject("userEmail", userEmail);
        modelAndView.addObject("userFullName", userFullName);
        modelAndView.addObject("userAcc", userAcc);
        modelAndView.addObject("userCosts", costs);*/

        BankAccountViewModel bankAccountViewModel = this.modelMapper.map(this.bankAccountService.findBankAccountByUser(principal.getName()),
                BankAccountViewModel.class);

        modelAndView.addObject("user", bankAccountViewModel);

        modelAndView.setViewName("/user/transaction");


        return modelAndView;
    }

  /*  @PostMapping("/profile/transactions")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView transactionByDate(ModelAndView modelAndView, Principal principal, @RequestParam("dateFrom") String dateFrom,
                                          @RequestParam("dateTo") String dateTo, @RequestParam("transaction") String transaction) throws ParseException, ParseException {
        Date convertDateFrom = new SimpleDateFormat("yyyy-MM-dd").parse(dateFrom);
        Date convertDateTo = new SimpleDateFormat("yyyy-MM-dd").parse(dateTo);

        java.sql.Date fromDate = new java.sql.Date(convertDateFrom.getTime());
        java.sql.Date toDate = new java.sql.Date(convertDateTo.getTime());

        System.out.println();

        switch (transaction){
            case "costs":
                List<CostServiceModel> costsView =  showCosts(principal.getName(), fromDate, toDate);
                modelAndView.addObject("costsView", costsView);
                break;
            case "incomes":
                List<IncomeServiceModel> incomesView = showIncomes(principal.getName(), fromDate, toDate);
                modelAndView.addObject("incomesView", incomesView);
                break;
        }
        modelAndView.setViewName("redirect:/user/profile/transactions");
        return modelAndView;
    }


    private List<CostServiceModel> showCosts(String username, java.sql.Date fromDate, java.sql.Date toDate){
        return  this.costService.userCostsBetweenDates(username, fromDate, toDate)
                .stream()
                .map(costs -> this.modelMapper.map(costs, CostServiceModel.class))
                .collect(Collectors.toList());


    }

    private List<IncomeServiceModel> showIncomes(String username, java.sql.Date fromDate, java.sql.Date toDate){
        return this.costService.userCostsBetweenDates(username, fromDate, toDate)
                .stream()
                .map(incomes -> this.modelMapper.map(incomes, IncomeServiceModel.class))
                .collect(Collectors.toList());
    }
*/
}
