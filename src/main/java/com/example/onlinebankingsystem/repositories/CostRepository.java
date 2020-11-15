package com.example.onlinebankingsystem.repositories;

import com.example.onlinebankingsystem.domain.entities.Cost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CostRepository extends JpaRepository<Cost, Integer> {

    List<Cost> findAllBySender_User_UsernameOrderByDateDesc(String username);

}
