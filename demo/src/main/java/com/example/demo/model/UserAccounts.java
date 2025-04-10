package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserAccounts{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long user_id; 
    private String email;
    private String password;
    private String firstName;
    private String LastName;
    private String phoneNumber;
    private String role;
}