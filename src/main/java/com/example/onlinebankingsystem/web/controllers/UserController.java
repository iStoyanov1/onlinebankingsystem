package com.example.onlinebankingsystem.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/register")
    public ModelAndView modelAndView(ModelAndView modelAndView){
        modelAndView.setViewName("register-user");
        return modelAndView;
    }
}
