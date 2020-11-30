package com.example.onlinebankingsystem.web.view.controllers.user;

import com.example.onlinebankingsystem.data.models.binding.BillPaymentBindingModel;
import com.example.onlinebankingsystem.data.models.services.BankAccountServiceModel;
import com.example.onlinebankingsystem.data.models.services.BillServiceModel;
import com.example.onlinebankingsystem.messages.OutputMessages;
import com.example.onlinebankingsystem.services.interfaces.BankAccountService;
import com.example.onlinebankingsystem.services.interfaces.BillsService;
import com.example.onlinebankingsystem.services.interfaces.UserService;
import com.example.onlinebankingsystem.web.view.controllers.base.BaseController;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;

import static com.example.onlinebankingsystem.messages.OutputMessages.FAILED_BILL_PAYMENT;
import static com.example.onlinebankingsystem.messages.OutputMessages.SUCCESS_BILL_PAYMENT;

@Controller
@RequestMapping("/user/bills")
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

    @GetMapping("/payment")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView billsView(ModelAndView modelAndView, Principal principal){

        BankAccountServiceModel user = this.bankAccountService.findBankAccountByUser(principal.getName());

        BillPaymentBindingModel bills = this.modelMapper.map(user, BillPaymentBindingModel.class);

        modelAndView.addObject("model", bills);
        modelAndView.addObject("user", user);
        return super.view("/user/bills-payment", modelAndView);
    }

    @PostMapping("/payment")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView billsConfirm(@Valid @ModelAttribute("model")BillPaymentBindingModel model, BindingResult bindingResult,
                                     Principal principal, ModelAndView modelAndView){

        BankAccountServiceModel user = this.bankAccountService.findBankAccountByUser(principal.getName());

        if (bindingResult.hasErrors()){
            modelAndView.addObject("model", model);
            modelAndView.addObject("user", user);
            return super.view("/user/bills-payment", modelAndView);
        }

        BillServiceModel billServiceModel = this.modelMapper.map(model, BillServiceModel.class);



        if (model.getQuantity() > user.getBalance()){
            modelAndView.addObject("failedMessage", FAILED_BILL_PAYMENT);
            modelAndView.addObject("user", user);
            return super.view("/user/bills-payment", modelAndView);
        }

        billServiceModel.setDate(Date.valueOf(LocalDate.now()));
        billServiceModel.setAccount(user);

        this.bankAccountService.reduceBalance(model.getQuantity(), user);
        this.billsService.saveBill(billServiceModel);
        modelAndView.addObject("user", user);
        modelAndView.addObject("successMessage", SUCCESS_BILL_PAYMENT);
        return super.view("/user/bills-payment", modelAndView);
    }
}
