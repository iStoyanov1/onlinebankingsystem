package com.example.onlinebankingsystem.web.view.controllers.user;

import com.example.onlinebankingsystem.data.models.binding.UserTransferBindingModel;
import com.example.onlinebankingsystem.data.models.services.BankAccountServiceModel;
import com.example.onlinebankingsystem.data.models.services.CostServiceModel;
import com.example.onlinebankingsystem.data.models.services.IncomeServiceModel;
import com.example.onlinebankingsystem.data.models.services.UserServiceModel;
import com.example.onlinebankingsystem.services.interfaces.BankAccountService;
import com.example.onlinebankingsystem.services.interfaces.CostService;
import com.example.onlinebankingsystem.services.interfaces.IncomeService;
import com.example.onlinebankingsystem.services.interfaces.UserService;
import com.example.onlinebankingsystem.web.view.controllers.base.BaseController;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



import javax.validation.Valid;
import java.security.Principal;

import static com.example.onlinebankingsystem.messages.OutputMessages.*;

@Controller
@RequestMapping("/user")
public class TransferController extends BaseController {

    private static final String DETAIL = "Превод";

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final BankAccountService bankAccountService;
    private final CostService costService;
    private final IncomeService incomeService;

    public TransferController(UserService userService, ModelMapper modelMapper, BankAccountService bankAccountService,
                              CostService costService, IncomeService incomeService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.bankAccountService = bankAccountService;
        this.costService = costService;
        this.incomeService = incomeService;
    }

    @GetMapping("/transfer")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView transferView(ModelAndView modelAndView, Principal principal){

        UserServiceModel userByUsername = this.userService.findUserByUsername(principal.getName());

        UserServiceModel userById = this.userService.findUserById(userByUsername.getId());

        modelAndView.addObject("user", userById);

        return super.view("/user/transfer/money-transfer", modelAndView);
    }
    @GetMapping("/transfer/outside/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView paymentOutsideView(@ModelAttribute(name = "userTransfer") UserTransferBindingModel userTransfer,
                                           @PathVariable int id, ModelAndView modelAndView){

        BankAccountServiceModel bankAcc = this.bankAccountService.findBankAccountByUserId(id);

        modelAndView.addObject("userTransfer", userTransfer);
        modelAndView.addObject("user", bankAcc);
        return super.view("/user/transfer/transfer-outside", modelAndView);
    }

    @PostMapping("/transfer/outside/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView paymentOutside(@Valid @ModelAttribute(name = "userTransfer") UserTransferBindingModel userTransfer,
                                       BindingResult bindingResult, ModelAndView modelAndView,
                                       @PathVariable int id){
        BankAccountServiceModel bankAcc = this.bankAccountService.findBankAccountByUserId(id);
        modelAndView.addObject("user", bankAcc);

        if (bindingResult.hasErrors()){
            modelAndView.addObject("userTransfer", userTransfer);
            return super.view("/user/transfer/transfer-outside", modelAndView);
        }

        if (userTransfer.getQuantity() > bankAcc.getBalance()){
            return getModelAndView(modelAndView, "failedMessage", FAILED_TRANSFER_MESSAGE);
        }

        CostServiceModel cost = this.modelMapper.map(userTransfer,
                CostServiceModel.class);

        this.bankAccountService.reduceBalance(userTransfer.getQuantity(), bankAcc);

        this.costService.setCost(cost, DETAIL, bankAcc);

        return getModelAndView(modelAndView, "successMessage", SUCCESS_TRANSFER_MESSAGE);
    }

    @GetMapping("/transfer/bank/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView paymentToBankAccView(@ModelAttribute(name = "userTransfer") UserTransferBindingModel userTransfer,
                                         @PathVariable int id, ModelAndView modelAndView, Principal principal){

        BankAccountServiceModel bankAcc = this.bankAccountService.findBankAccountByUserId(id);

        modelAndView.addObject("userTransfer", userTransfer);
        modelAndView.addObject("user", bankAcc);
        return super.view("/user/transfer/transfer-bank", modelAndView);

    }

    @PostMapping("/transfer/bank/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView paymentToBank(@Valid @ModelAttribute(name = "userTransfer") UserTransferBindingModel userTransferBindingModel,
                                       BindingResult bindingResult, @PathVariable int id, ModelAndView modelAndView, Principal principal){

        BankAccountServiceModel senderBankAcc = this.bankAccountService.findBankAccountByUserId(id);

        modelAndView.addObject("user", senderBankAcc);

        if (bindingResult.hasErrors()){
            modelAndView.addObject("userTransfer", userTransferBindingModel);
            return super.view("/user/transfer/transfer-bank", modelAndView);
        }

        BankAccountServiceModel recipientBankAcc;

        try{
            recipientBankAcc = this.bankAccountService.findBankAccByAccNumber(userTransferBindingModel.getRecipient());
            this.userService.findUserByFullName(userTransferBindingModel.getRecipientName());

        }catch (Exception e){
            return getModelAndView(modelAndView, "notFoundMessage", USER_NOT_FOUND);
        }

        if (userTransferBindingModel.getQuantity() > senderBankAcc.getBalance()){
            return getModelAndView(modelAndView, "failedMessage", FAILED_TRANSFER_MESSAGE);
        }

        CostServiceModel cost = this.modelMapper.map(userTransferBindingModel,
                CostServiceModel.class);

        IncomeServiceModel incomeServiceModel = this.modelMapper.map(userTransferBindingModel, IncomeServiceModel.class);

        this.bankAccountService.reduceBalance(userTransferBindingModel.getQuantity(), senderBankAcc);
        this.bankAccountService.incomeBalance(userTransferBindingModel.getQuantity(), recipientBankAcc);

        this.incomeService.setIncome(incomeServiceModel, recipientBankAcc, DETAIL);

        this.costService.setCost(cost, DETAIL, senderBankAcc);

        return getModelAndView(modelAndView, "successMessage", SUCCESS_TRANSFER_MESSAGE);
    }

    private ModelAndView getModelAndView(ModelAndView modelAndView, String message, String param) {
        modelAndView.addObject(message, param);
        return super.view("/user/transfer/transfer-bank", modelAndView);
    }

}
