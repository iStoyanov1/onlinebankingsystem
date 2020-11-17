package com.example.onlinebankingsystem.services.interfaces;

import com.example.onlinebankingsystem.domain.models.services.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserServiceModel findUserByUsername(String username);

    UserServiceModel editUsername(String oldUsername, String newUsername);

    UserServiceModel editPhone(String username, String phone);

    UserServiceModel editPassword(UserServiceModel userServiceModel, String password);

    UserServiceModel editEmail(String username, String email);

}
