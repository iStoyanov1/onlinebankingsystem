package com.example.onlinebankingsystem.web.view.controllers.user;

import com.example.onlinebankingsystem.data.models.binding.BillPaymentBindingModel;
import com.example.onlinebankingsystem.data.models.services.BankAccountServiceModel;
import com.example.onlinebankingsystem.data.models.services.BillServiceModel;
import com.example.onlinebankingsystem.services.interfaces.BankAccountService;
import com.example.onlinebankingsystem.services.interfaces.BillsService;
import com.example.onlinebankingsystem.services.interfaces.UserService;
import com.example.onlinebankingsystem.web.view.controllers.base.BaseController;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;

@Controller
@RequestMapping("/user")
public class BillsPaymentController extends BaseController {

    private final UserService userService;
    private final BankAccountService bankAccountService;
    private final ModelMapper modelMapper;
    private final BillsService billsService;

    public BillsPaymentController(UserService userService, BankAccountService bankAccountService, ModelMapper modelMapper, BillsService billsService) {
        this.userService = userService;
        this.bankAccountService = bankAccountService;
        this.modelMapper = modelMapper;
        this.billsService = billsService;
    }

    @GetMapping("/bills")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView billsView(ModelAndView modelAndView, Principal principal){

        BankAccountServiceModel user = this.bankAccountService.findBankAccountByUser(principal.getName());
        modelAndView.addObject("user", user);
        return super.view("/user/bills", modelAndView);
    }

    @PostMapping("/bills")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView billsConfirm(@ModelAttribute("model")BillPaymentBindingModel model, Principal principal){
        BillServiceModel billServiceModel = this.modelMapper.map(model, BillServiceModel.class);

        BankAccountServiceModel bankAccountServiceModel = this.bankAccountService.findBankAccountByUser(principal.getName());

        billServiceModel.setDate(Date.valueOf(LocalDate.now()));
        billServiceModel.setAccount(bankAccountServiceModel);

        this.bankAccountService.reduceBalance(model.getQuantity(), bankAccountServiceModel);
        this.billsService.saveBill(billServiceModel);
        return null;

    }
}
