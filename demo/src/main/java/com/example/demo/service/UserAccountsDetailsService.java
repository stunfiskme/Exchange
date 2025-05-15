package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.SignupRequestDTO;
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
public UserAccounts save(SignupRequestDTO signupRequestDTO) throws Exception{
  UserAccounts ua = new UserAccounts();
  ua.setEmail(signupRequestDTO.getEmail());
  ua.setFirstName(signupRequestDTO.getFirstName());
  ua.setLastName(signupRequestDTO.getLastName());
  ua.setPhoneNumber(signupRequestDTO.getPhoneNumber());
  //set role
  ua.getRoles().add(roleService.findByName("USER"));
  //set password
  ua.setPassword(signupRequestDTO.getPassword());
  return userRepository.save(ua);
}

    
}
