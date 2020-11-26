package com.example.onlinebankingsystem.web.view.controllers.usercontroler;

import com.example.onlinebankingsystem.data.models.binding.UserEditPasswordBindingModel;
import com.example.onlinebankingsystem.data.models.services.CostServiceModel;
import com.example.onlinebankingsystem.data.models.services.IncomeServiceModel;
import com.example.onlinebankingsystem.data.models.services.UserServiceModel;
import com.example.onlinebankingsystem.web.view.models.BankAccountViewModel;
import com.example.onlinebankingsystem.services.interfaces.BankAccountService;
import com.example.onlinebankingsystem.services.interfaces.CostService;
import com.example.onlinebankingsystem.services.interfaces.IncomeService;
import com.example.onlinebankingsystem.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    @PreAuthorize("isAuthenticated()")
    public ModelAndView home(Principal principal, ModelAndView modelAndView) {

        UserServiceModel user = this.userService.findUserByUsername(principal.getName());
        BankAccountViewModel bankAccount = this.modelMapper.
                map(this.bankAccountService
                                .findBankAccountByUser(user.getUsername()),
                        BankAccountViewModel.class);
        List<IncomeServiceModel> userIncomes = this.incomeService.findLastIncomesByUser(principal.getName());
        List<CostServiceModel> userCosts = this.costService.userLastCosts(principal.getName());
        modelAndView.addObject("userCosts", userCosts);
        modelAndView.addObject("userIncomes", userIncomes);
        modelAndView.addObject("user", bankAccount);
        modelAndView.setViewName("/user/profile");
        return modelAndView;
    }

    @GetMapping("/profile/edit-phone")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editPhone(Principal principal, ModelAndView modelAndView) {
        UserServiceModel user = this.userService.findUserByUsername(principal.getName());
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/user/edit/edit-phone");
        return modelAndView;
    }

    @PostMapping("/profile/edit-phone")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editPhoneConfirm(@RequestParam("newPhone") String phone, Principal principal, ModelAndView modelAndView) {
        this.userService.editPhone(principal.getName(), phone);
        modelAndView.setViewName("redirect:/user/profile");
        return modelAndView;
    }

    @GetMapping("/profile/edit-password")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editPassword(Principal principal, ModelAndView modelAndView) {
        UserServiceModel user = this.userService.findUserByUsername(principal.getName());

        UserEditPasswordBindingModel model = this.modelMapper.map(user, UserEditPasswordBindingModel.class);

        modelAndView.addObject("model", model);
        modelAndView.setViewName("/user/edit/edit-password");
        return modelAndView;
    }

    @PostMapping("/profile/edit-password")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editPasswordConfirm(@ModelAttribute("model") UserEditPasswordBindingModel model, ModelAndView modelAndView) {

        if (!model.getPassword().equals(model.getConfirmPassword())) {
            modelAndView.setViewName("/user/edit/edit-password");
            return modelAndView;
        }
        UserServiceModel userServiceModel = this.modelMapper.map(model, UserServiceModel.class);

        this.userService.editPassword(userServiceModel, model.getOldPassword());

        modelAndView.setViewName("redirect:/user/profile");
        return modelAndView;
    }

    @GetMapping("/profile/edit-email")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editEmail(Principal principal, ModelAndView modelAndView) {
        UserServiceModel user = this.userService.findUserByUsername(principal.getName());
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/user/edit/edit-email");
        return modelAndView;
    }

    @PostMapping("/profile/edit-email")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editEmailConfirm(@RequestParam("email") String phone, Principal principal, ModelAndView modelAndView) {
        this.userService.editEmail(principal.getName(), phone);
        modelAndView.setViewName("redirect:/user/profile");
        return modelAndView;
    }


}
