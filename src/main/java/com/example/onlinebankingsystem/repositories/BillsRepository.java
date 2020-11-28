package com.example.onlinebankingsystem.repositories;

import com.example.onlinebankingsystem.data.entities.Bills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillsRepository extends JpaRepository<Bills, Integer> {
}
