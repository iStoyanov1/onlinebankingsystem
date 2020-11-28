package com.example.onlinebankingsystem.services.interfaces;

import com.example.onlinebankingsystem.data.models.services.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserServiceModel findUserByUsername(String username);

    UserServiceModel editUsername(String oldUsername, String newUsername);

    UserServiceModel editPassword(UserServiceModel userServiceModel, String password);

    UserServiceModel editEmail(UserServiceModel userServiceModel, String email);

    UserServiceModel editPhone(UserServiceModel userServiceModel, String phone);

    UserServiceModel findUserById(int id);

}
