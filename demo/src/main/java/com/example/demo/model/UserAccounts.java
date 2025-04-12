package com.example.demo.model;

import java.util.List;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserAccounts{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id; 
    private String email;
    private String password;
    private String firstName;
    private String LastName;
    private String phoneNumber;
    @ColumnDefault("USER")
    private String role;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Recipe> recipes;
}