package com.example.onlinebankingsystem.web.view.controllers.user;

import com.example.onlinebankingsystem.data.models.binding.UserEditEmailBindingModel;
import com.example.onlinebankingsystem.data.models.binding.UserEditPasswordBindingModel;
import com.example.onlinebankingsystem.data.models.binding.UserEditPhoneBindingModel;
import com.example.onlinebankingsystem.data.models.services.CostServiceModel;
import com.example.onlinebankingsystem.data.models.services.IncomeServiceModel;
import com.example.onlinebankingsystem.data.models.services.UserServiceModel;
import com.example.onlinebankingsystem.web.view.controllers.base.BaseController;
import com.example.onlinebankingsystem.web.view.models.BankAccountViewModel;
import com.example.onlinebankingsystem.services.interfaces.BankAccountService;
import com.example.onlinebankingsystem.services.interfaces.CostService;
import com.example.onlinebankingsystem.services.interfaces.IncomeService;
import com.example.onlinebankingsystem.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

import static com.example.onlinebankingsystem.messages.OutputMessages.*;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final BankAccountService bankAccountService;
    private final IncomeService incomeService;
    private final CostService costService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserService userService, ModelMapper modelMapper, BankAccountService bankAccountService, IncomeService incomeService, CostService costService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.bankAccountService = bankAccountService;
        this.incomeService = incomeService;
        this.costService = costService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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

        return super.view("/user/profile", modelAndView);
    }


    @GetMapping("/edit/password")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editPassword(Principal principal, ModelAndView modelAndView) {
        UserServiceModel user = this.userService.findUserByUsername(principal.getName());

        UserEditPasswordBindingModel model = this.modelMapper.map(user, UserEditPasswordBindingModel.class);

        modelAndView.addObject("model", model);

        return super.view("/user/edit/edit-password", modelAndView);
    }

    @PostMapping("/edit/password")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editPasswordConfirm(@Valid @ModelAttribute("model") UserEditPasswordBindingModel model,
                                            BindingResult bindingResult,
                                            ModelAndView modelAndView,
                                            Principal principal) {

        UserServiceModel userServiceModel = this.userService.findUserByUsername(principal.getName());

        if (!this.bCryptPasswordEncoder.matches(model.getOldPassword(), userServiceModel.getPassword())){
            return getModelAndView(modelAndView, "wrongMessage", WRONG_OLD_PASSWORD);
        }

        if (bindingResult.hasErrors()){
            modelAndView.addObject("model", model);
            return super.view("/user/edit/edit-password", modelAndView);
        }


        if (!model.getPassword().equals(model.getConfirmPassword())) {
            return getModelAndView(modelAndView, "failedMessage", FAILED_EDIT_PASSWORD);
        }

        userServiceModel = this.modelMapper.map(model, UserServiceModel.class);
        this.userService.editPassword(userServiceModel, model.getOldPassword());

        return getModelAndView(modelAndView, "successMessage", SUCCESS_EDIT_PASSWORD);
    }


    @GetMapping("/edit/email")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editEmail(Principal principal, ModelAndView modelAndView) {


        UserEditEmailBindingModel user = this.modelMapper
                .map(this.userService.findUserByUsername(principal.getName()), UserEditEmailBindingModel.class);
        //UserServiceModel user = this.userService.findUserByUsername(principal.getName());
        modelAndView.addObject("model", user);
        return super.view("/user/edit/edit-email", modelAndView);
    }

    @PostMapping("/edit/email")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editEmailConfirm(@Valid @ModelAttribute("model") UserEditEmailBindingModel model, BindingResult bindingResult
                                                                , Principal principal, ModelAndView modelAndView) {

        UserServiceModel userServiceModel = this.userService.findUserByUsername(principal.getName());
        System.out.println();
        if (bindingResult.hasErrors()){
            modelAndView.addObject("model", model);
            return super.view("/user/edit/edit-email", modelAndView);
        }

       // user = this.modelMapper.map(model, UserServiceModel.class);


        this.userService.editEmail(userServiceModel, model.getNewEmail());
      //  modelAndView.addObject("model", userServiceModel);
        modelAndView.addObject("success", SUCCESS_EDIT_EMAIL);
        return super.view("/user/edit/edit-email", modelAndView);
    }


    @GetMapping("/edit/phone")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editPhone(Principal principal, ModelAndView modelAndView) {
        UserServiceModel user = this.userService.findUserByUsername(principal.getName());
        modelAndView.addObject("model", user);
        return super.view("/user/edit/edit-phone", modelAndView);
    }

    @PostMapping("/edit/phone")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editPhoneConfirm(@Valid @ModelAttribute("model")UserEditPhoneBindingModel model,
                                         BindingResult bindingResult, Principal principal, ModelAndView modelAndView) {
        UserServiceModel userServiceModel = this.userService.findUserByUsername(principal.getName());

        if (bindingResult.hasErrors()){
            modelAndView.addObject("model", model);
            return super.view("/user/edit/edit-phone", modelAndView);
        }
        this.userService.editPhone(userServiceModel, model.getPhone());
        modelAndView.addObject("success", SUCCESS_EDIT_PHONE);
        return super.view("/user/edit/edit-phone", modelAndView);
    }

    private ModelAndView getModelAndView(ModelAndView modelAndView, String failedMessage, String failedEditPassword) {
        modelAndView.addObject(failedMessage, failedEditPassword);
        return super.view("/user/edit/edit-password", modelAndView);
    }

}
