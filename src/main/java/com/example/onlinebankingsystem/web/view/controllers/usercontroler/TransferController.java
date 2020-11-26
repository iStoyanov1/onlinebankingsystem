package com.example.onlinebankingsystem.web.view.controllers.usercontroler;

import com.example.onlinebankingsystem.data.models.binding.UserTransferBindingModel;
import com.example.onlinebankingsystem.data.models.services.BankAccountServiceModel;
import com.example.onlinebankingsystem.data.models.services.CostServiceModel;
import com.example.onlinebankingsystem.data.models.services.UserServiceModel;
import com.example.onlinebankingsystem.services.interfaces.BankAccountService;
import com.example.onlinebankingsystem.services.interfaces.CostService;
import com.example.onlinebankingsystem.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



import javax.validation.Valid;
import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;

import static com.example.onlinebankingsystem.messages.OutputMessages.*;

@Controller
@RequestMapping("/user")
public class TransferController {

    private static final String COST_DETAIL = "Превод";

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final BankAccountService bankAccountService;
    private final CostService costService;

    public TransferController(UserService userService, ModelMapper modelMapper, BankAccountService bankAccountService, CostService costService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.bankAccountService = bankAccountService;
        this.costService = costService;
    }

    @GetMapping("/profile/transfer")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView transferView(ModelAndView modelAndView, Principal principal){

        UserServiceModel userByUsername = this.userService.findUserByUsername(principal.getName());

        UserServiceModel userById = this.userService.findUserById(userByUsername.getId());

        modelAndView.addObject("user", userById);

        modelAndView.setViewName("/user/money-transfer");
        return modelAndView;
    }
    @GetMapping("/profile/transfer/outside/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView paymentOutsideView(@ModelAttribute(name = "userTransfer") UserTransferBindingModel userTransfer, @PathVariable int id, ModelAndView modelAndView){

        BankAccountServiceModel bankAcc = this.bankAccountService.findBankAccountByUserId(id);

        modelAndView.addObject("userTransfer", userTransfer);
        modelAndView.addObject("user", bankAcc);
        modelAndView.setViewName("/user/transfer-outside");
        return modelAndView;
    }

    @PostMapping("/profile/transfer/outside/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView paymentOutside(@Valid @ModelAttribute(name = "userTransfer") UserTransferBindingModel userTransfer,
                                       BindingResult bindingResult, ModelAndView modelAndView,
                                       @PathVariable int id){
        BankAccountServiceModel bankAcc = this.bankAccountService.findBankAccountByUserId(id);
        modelAndView.addObject("user", bankAcc);
        System.out.println();

        if (bindingResult.hasErrors()){
            modelAndView.addObject("userTransfer", userTransfer);
            modelAndView.setViewName("/user/transfer-outside");
            return modelAndView;
        }

        if (userTransfer.getQuantity() > bankAcc.getBalance()){
            return getModelAndView(modelAndView, "failedMessage", FAILED_TRANSFER_MESSAGE);
        }

        CostServiceModel cost = this.modelMapper.map(userTransfer,
                CostServiceModel.class);

        cost.setDate(Date.valueOf(LocalDate.now()));
        cost.setSender(this.bankAccountService.findBankAccByAccNumber(userTransfer.getSender()));
        cost.setDetails(COST_DETAIL);
        this.bankAccountService.reduceMoney(cost.getQuantity(), userTransfer.getSender());
        this.costService.setCost(cost);

        return getModelAndView(modelAndView, "successMessage", SUCCESS_TRANSFER_MESSAGE);
    }

    private ModelAndView getModelAndView(ModelAndView modelAndView, String message, String param) {
        modelAndView.addObject(message, param);
        modelAndView.setViewName("/user/transfer-outside");
        return modelAndView;
    }

}
