package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserAccounts;
import com.example.demo.repository.UserRepository;

@Service
public class UserAccountsDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;
   

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

       Optional<UserAccounts> userAccount = userRepository.findByEmail(email);
       if(userAccount.isPresent()){
var userObj = userAccount.get();
return User.builder()
.username(userObj.getEmail())
.password(userObj.getPassword())
.roles(userObj.getRole())
.build();
       }
       else{
         throw new UsernameNotFoundException("User does not exists!");
    }
}
    
}
