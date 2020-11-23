package com.example.onlinebankingsystem.repositories;

import com.example.onlinebankingsystem.domain.entities.BankAccount;
import com.example.onlinebankingsystem.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

   // BankAccount findBankAccountByUser(User user);

    BankAccount findBankAccountByUser_Username(String username);

    BankAccount findBankAccountByUser_Id(int id);

}
