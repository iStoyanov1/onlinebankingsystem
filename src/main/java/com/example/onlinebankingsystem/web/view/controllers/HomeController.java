package com.example.onlinebankingsystem.web.view.controllers;

import com.example.onlinebankingsystem.services.interfaces.UserService;
import com.example.onlinebankingsystem.web.view.controllers.base.BaseController;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    public HomeController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/")
    @PreAuthorize("isAnonymous()")
    public ModelAndView homeView(ModelAndView modelAndView){
       return super.view("index");
    }


}
