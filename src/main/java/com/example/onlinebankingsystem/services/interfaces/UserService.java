package com.example.onlinebankingsystem.services.interfaces;

import com.example.onlinebankingsystem.domain.services.UserServiceModel;

public interface UserService {

    boolean registerUser(UserServiceModel userServiceModel);

    UserServiceModel findUserByEmail(String email);

    boolean loginUser(UserServiceModel userServiceModel);

}
