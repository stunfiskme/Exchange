package com.example.demo.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAccounts{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id; 
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String LastName;
    @Column(nullable = false)
    private String phoneNumber;

    //connection to recipes
    @OneToMany( mappedBy = "userAccount", cascade = CascadeType.ALL)
    private List<Recipe> recipes = new ArrayList<>();

    //
    @ManyToMany(cascade = CascadeType.MERGE ,fetch = FetchType.EAGER)
    @JoinTable(name = "user_accounts_roles",
            joinColumns = @JoinColumn(name = "UserAccounts_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
}