package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class Role {
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name; 

    @ManyToMany(mappedBy = "roles")
    private Set<UserAccounts> users = new HashSet<>();
}
