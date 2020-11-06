package com.example.onlinebankingsystem.web.controllers;

import com.example.onlinebankingsystem.domain.models.binding.UserLoginBindingModel;
import com.example.onlinebankingsystem.domain.models.services.UserServiceModel;
import com.example.onlinebankingsystem.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    public HomeController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/")
    public ModelAndView homeView(ModelAndView modelAndView){
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @PostMapping("/")
    public ModelAndView login(@ModelAttribute UserLoginBindingModel userLoginBindingModel, ModelAndView modelAndView){
        UserServiceModel userServiceModel = this.modelMapper.map(userLoginBindingModel, UserServiceModel.class);

        if (!this.userService.loginUser(userServiceModel)){
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }else{
            modelAndView.setViewName("user-home");
            return modelAndView;
        }
    }
}
