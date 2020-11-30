package com.example.onlinebankingsystem.web.view.controllers.user;

import com.example.onlinebankingsystem.data.models.binding.UserTransferBindingModel;
import com.example.onlinebankingsystem.data.models.services.BankAccountServiceModel;
import com.example.onlinebankingsystem.data.models.services.CostServiceModel;
import com.example.onlinebankingsystem.data.models.services.UserServiceModel;
import com.example.onlinebankingsystem.services.interfaces.BankAccountService;
import com.example.onlinebankingsystem.services.interfaces.CostService;
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
    public ModelAndView paymentOutsideView(@ModelAttribute(name = "userTransfer") UserTransferBindingModel userTransfer, @PathVariable int id, ModelAndView modelAndView){

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

        this.costService.setCost(cost, COST_DETAIL);

        return getModelAndView(modelAndView, "successMessage", SUCCESS_TRANSFER_MESSAGE);
    }

    private ModelAndView getModelAndView(ModelAndView modelAndView, String message, String param) {
        modelAndView.addObject(message, param);
        return super.view("/user/transfer/transfer-outside", modelAndView);
    }

}
