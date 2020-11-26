package com.example.onlinebankingsystem.services;

import com.example.onlinebankingsystem.data.entities.User;
import com.example.onlinebankingsystem.data.models.services.UserServiceModel;
import com.example.onlinebankingsystem.repositories.UserRepository;
import com.example.onlinebankingsystem.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findUserByUsername(username);
    }

    @Override
    public UserServiceModel findUserByUsername(String username) {
        return this.modelMapper.map(this.userRepository.findUserByUsername(username), UserServiceModel.class);
    }

    @Override
    public UserServiceModel editUsername(String oldUsername, String username) {
        User user = this.userRepository.findUserByUsername(oldUsername);

        if (username == null  || username.equals("")){
            throw new IllegalArgumentException();
        }
        user.setUsername(username);

        return this.modelMapper.map(this.userRepository.saveAndFlush(user), UserServiceModel.class);
    }

    @Override
    public UserServiceModel editPhone(String username, String phone) {
        User user = this.userRepository.findUserByUsername(username);

        if (phone == null || phone.equals("")){
            throw new IllegalArgumentException();
        }

        user.setPhone(phone);

        return this.modelMapper.map(this.userRepository.saveAndFlush(user), UserServiceModel.class);
    }

    @Override
    public UserServiceModel editPassword(UserServiceModel userServiceModel, String password) {
        User user = this.userRepository.findUserByUsername(userServiceModel.getUsername());

        if (!this.bCryptPasswordEncoder.matches(password, user.getPassword())){
            throw new IllegalArgumentException();
        }
        user.setPassword(userServiceModel.getPassword() != null ?
                this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()) :
                user.getPassword());
        user.setPassword(userServiceModel.getPassword().equals("") ?
                this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()) :
                user.getPassword());

        return this.modelMapper.map(this.userRepository.saveAndFlush(user), UserServiceModel.class);

    }

    @Override
    public UserServiceModel editEmail(String username, String email) {
        User user = this.userRepository.findUserByUsername(username);

        if (email == null || email.equals("")){
            throw new IllegalArgumentException();
        }

        user.setEmail(email);

        return this.modelMapper.map(this.userRepository.saveAndFlush(user), UserServiceModel.class);
    }

    @Override
    public UserServiceModel findUserById(int id) {

        return this.modelMapper.map(this.userRepository.findUserById(id), UserServiceModel.class);
    }
}
