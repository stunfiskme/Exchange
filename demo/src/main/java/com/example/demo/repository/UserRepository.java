package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserAccounts;

@Repository
public interface UserRepository extends JpaRepository<UserAccounts, Long>{
    Optional<UserAccounts> findByEmail(String email);
}
