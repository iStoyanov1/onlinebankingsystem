package com.example.onlinebankingsystem.repositories;

import com.example.onlinebankingsystem.data.entities.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Integer> {


    List<Income> findAllByRecipient_User_UsernameOrderByDateDesc(String username);

    List<Income> findAllByRecipient_User_UsernameAndDateBetweenOrderByDateDesc(String username, Date from, Date to);




}
