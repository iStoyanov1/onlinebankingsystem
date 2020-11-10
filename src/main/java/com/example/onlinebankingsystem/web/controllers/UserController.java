package com.example.onlinebankingsystem.web.controllers;

import com.example.onlinebankingsystem.domain.models.binding.UserLoginBindingModel;
import com.example.onlinebankingsystem.domain.models.services.UserServiceModel;
import com.example.onlinebankingsystem.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {


    @GetMapping("/home")
    public ModelAndView home(ModelAndView modelAndView){
        modelAndView.setViewName("user/home");
        return modelAndView;
    }


}
