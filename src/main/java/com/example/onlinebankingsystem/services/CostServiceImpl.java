package com.example.onlinebankingsystem.services;

import com.example.onlinebankingsystem.domain.models.services.BankAccountServiceModel;
import com.example.onlinebankingsystem.domain.models.services.CostServiceModel;
import com.example.onlinebankingsystem.domain.models.services.UserServiceModel;
import com.example.onlinebankingsystem.repositories.CostRepository;
import com.example.onlinebankingsystem.services.interfaces.BankAccountService;
import com.example.onlinebankingsystem.services.interfaces.CostService;
import com.example.onlinebankingsystem.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CostServiceImpl implements CostService {

    private final ModelMapper modelMapper;
    private final BankAccountService bankAccountService;
    private final CostRepository costRepository;
    private final UserService userService;

    public CostServiceImpl(ModelMapper modelMapper, BankAccountService bankAccountService, CostRepository costRepository, UserService userService) {
        this.modelMapper = modelMapper;
        this.bankAccountService = bankAccountService;
        this.costRepository = costRepository;
        this.userService = userService;
    }

    @Override
    public List<CostServiceModel> userLastCosts(String username) {
        UserServiceModel user = this.userService.findUserByUsername(username);
        BankAccountServiceModel bankAccount = this.bankAccountService.findBankAccountByUser(user.getUsername());

        return this.costRepository.findAllBySender_User_UsernameOrderByDateDesc(bankAccount.getUser().getUsername())
                .stream()
                .limit(3)
                .map(cost -> this.modelMapper.map(cost, CostServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public List<CostServiceModel> userAllCosts(String username) {
        /*UserServiceModel user = this.userService.findUserByUsername(username);

        BankAccountServiceModel bankAccount = this.bankAccountService.findBankAccountByUser(user.getUsername());*/

        return this.costRepository.findAllBySender_User_UsernameOrderByDateDesc(username)
                .stream()
                .map(cost -> this.modelMapper.map(cost, CostServiceModel.class)).collect(Collectors.toList());

    }

    @Override
    public List<CostServiceModel> userCostsBetweenDates(String username, Date from, Date to) {
        return this.costRepository.findAllBySender_User_UsernameAndDateBetweenOrderByDateDesc(username, from, to)
                .stream()
                .map(cost -> this.modelMapper.map(cost, CostServiceModel.class)).collect(Collectors.toList());
    }
}
