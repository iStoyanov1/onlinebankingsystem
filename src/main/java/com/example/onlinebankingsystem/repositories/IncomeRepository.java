package com.example.onlinebankingsystem.repositories;

import com.example.onlinebankingsystem.domain.entities.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Integer> {


    List<Income> findAllByRecipient_User_UsernameOrderByDateDesc(String username);



}
