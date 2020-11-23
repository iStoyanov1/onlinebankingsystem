package com.example.onlinebankingsystem.web.view.controllers.usercontroler;

import com.example.onlinebankingsystem.domain.models.services.BankAccountServiceModel;
import com.example.onlinebankingsystem.domain.models.services.UserServiceModel;
import com.example.onlinebankingsystem.services.interfaces.BankAccountService;
import com.example.onlinebankingsystem.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class TransferController {


    private final UserService userService;
    private final ModelMapper modelMapper;
    private final BankAccountService bankAccountService;

    public TransferController(UserService userService, ModelMapper modelMapper, BankAccountService bankAccountService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.bankAccountService = bankAccountService;
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
    @GetMapping("/profile/transfer/payment/other/{id}")
    public ModelAndView paymentOtherView(@PathVariable int id, ModelAndView modelAndView){


        return modelAndView;
    }
}
