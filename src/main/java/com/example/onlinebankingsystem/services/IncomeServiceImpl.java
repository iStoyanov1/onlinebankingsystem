package com.example.onlinebankingsystem.services;

import com.example.onlinebankingsystem.domain.models.services.BankAccountServiceModel;
import com.example.onlinebankingsystem.domain.models.services.CostServiceModel;
import com.example.onlinebankingsystem.domain.models.services.IncomeServiceModel;
import com.example.onlinebankingsystem.domain.models.services.UserServiceModel;
import com.example.onlinebankingsystem.repositories.IncomeRepository;
import com.example.onlinebankingsystem.services.interfaces.BankAccountService;
import com.example.onlinebankingsystem.services.interfaces.IncomeService;
import com.example.onlinebankingsystem.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IncomeServiceImpl implements IncomeService{

    private final ModelMapper modelMapper;
    private final BankAccountService bankAccountService;
    private final IncomeRepository incomeRepository;
    private final UserService userService;

    public IncomeServiceImpl(ModelMapper modelMapper, BankAccountService bankAccountService, UserService userService, IncomeRepository incomeRepository, IncomeRepository incomeRepository1) {
        this.modelMapper = modelMapper;
        this.bankAccountService = bankAccountService;
        this.userService = userService;
        this.incomeRepository = incomeRepository1;
    }

    @Override
    public List<IncomeServiceModel> findAllIncomesByUser(String username) {
       UserServiceModel user = this.modelMapper.map(this.userService.findUserByUsername(username), UserServiceModel.class);

        BankAccountServiceModel bankAccount = this.modelMapper.map(this.bankAccountService.findBankAccountByUser(user.getUsername()), BankAccountServiceModel.class);

        return this.incomeRepository.findAllByRecipient_User_UsernameOrderByDateDesc(bankAccount.getUser().getUsername())
                .stream()
                .limit(3)
                .map(income -> this.modelMapper.map(income, IncomeServiceModel.class))
                .collect(Collectors.toList());

    }

    @Override
    public List<IncomeServiceModel> findLastIncomesByUser(String username) {

        return this.incomeRepository.findAllByRecipient_User_UsernameOrderByDateDesc(username)
                .stream()
                .map(income -> this.modelMapper.map(income, IncomeServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<IncomeServiceModel> userIncomeBetweenDates(String username, Date from, Date to) {
        return this.incomeRepository.findAllByRecipient_User_UsernameAndDateBetweenOrderByDateDesc(username, from, to)
                .stream()
                .map(income -> this.modelMapper.map(income, IncomeServiceModel.class)).collect(Collectors.toList());
    }
}
