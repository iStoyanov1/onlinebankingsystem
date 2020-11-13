package com.example.onlinebankingsystem.web.controllers;

import com.example.onlinebankingsystem.domain.models.services.UserServiceModel;
import com.example.onlinebankingsystem.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/profile")
    public ModelAndView home(Principal principal, ModelAndView modelAndView){

        UserServiceModel user = this.userService.findUserByUsername(principal.getName());
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/user/profile");
        return modelAndView;
    }


}
