package com.example.onlinebankingsystem.services;

import com.example.onlinebankingsystem.data.entities.Bills;
import com.example.onlinebankingsystem.data.models.services.BillServiceModel;
import com.example.onlinebankingsystem.repositories.BillsRepository;
import com.example.onlinebankingsystem.services.interfaces.BillsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class BillsServiceImpl implements BillsService {

    private final ModelMapper modelMapper;
    private final BillsRepository billsRepository;


    public BillsServiceImpl(ModelMapper modelMapper, BillsRepository billsRepository) {
        this.modelMapper = modelMapper;
        this.billsRepository = billsRepository;
    }

    @Override
    public void saveBill(BillServiceModel billServiceModel) {
        this.billsRepository.saveAndFlush(this.modelMapper.map(billServiceModel, Bills.class));
    }


}
