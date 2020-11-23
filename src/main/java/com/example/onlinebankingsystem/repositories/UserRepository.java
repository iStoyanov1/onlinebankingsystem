package com.example.onlinebankingsystem.repositories;

import com.example.onlinebankingsystem.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByUsername(String username);

    User findByEmailAndPassword(String email, String password);

    User findUserById(int id);
}
