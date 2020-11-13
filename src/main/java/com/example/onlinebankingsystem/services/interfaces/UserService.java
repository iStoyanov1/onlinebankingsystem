package com.example.onlinebankingsystem.services.interfaces;

import com.example.onlinebankingsystem.domain.models.services.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserServiceModel findUserByUsername(String username);

}
