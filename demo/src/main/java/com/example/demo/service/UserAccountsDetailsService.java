package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserAccounts;
import com.example.demo.repository.UserRepository;

import security.CustomUserDetails;

@Service
public class UserAccountsDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserAccounts userObj = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    return new CustomUserDetails(userObj);
}

//save and add role to user
public UserAccounts save(UserAccounts ua) throws Exception{
  ua.getRoles().add(roleService.findByName("USER"));
  return userRepository.save(ua);
}

    
}
