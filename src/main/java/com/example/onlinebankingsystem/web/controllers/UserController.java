package com.example.onlinebankingsystem.web.controllers;

import com.example.onlinebankingsystem.domain.models.services.UserServiceModel;
import com.example.onlinebankingsystem.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/register")
    public ModelAndView registerView(ModelAndView modelAndView){
        modelAndView.setViewName("register-user");
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView registerUser(@ModelAttribute(name = "model") UserRegisterBindingModel model, ModelAndView modelAndView){
        if (!model.getPassword().equals(model.getConfirmPassword())){
            modelAndView.setViewName("register-user");
            return modelAndView;
        }
        UserServiceModel userServiceModel = this.modelMapper.map(model, UserServiceModel.class);

        this.userService.registerUser(userServiceModel);

        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

}
