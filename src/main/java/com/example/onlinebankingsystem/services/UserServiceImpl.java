package com.example.onlinebankingsystem.services;

import com.example.onlinebankingsystem.domain.entities.User;
import com.example.onlinebankingsystem.domain.services.UserServiceModel;
import com.example.onlinebankingsystem.repositories.UserRepository;
import com.example.onlinebankingsystem.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public boolean registerUser(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);

        try {
            this.userRepository.save(user);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public UserServiceModel findUserByEmail(String email) {
        return this.modelMapper.map(this.userRepository.findByEmail(email),
                UserServiceModel.class);
    }

    @Override
    public boolean loginUser(UserServiceModel userServiceModel) {
        if (this.userRepository.findByEmailAndPassword(userServiceModel.getEmail(), userServiceModel.getPassword()) != null) {
            return true;
        }

        return false;
    }


}
