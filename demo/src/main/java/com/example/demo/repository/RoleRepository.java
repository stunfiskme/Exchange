package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    Optional<Role> findByName(String name);
}
