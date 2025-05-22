package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.DTO.SignupRequestDTO;
import com.example.demo.service.UserAccountsDetailsService;

import jakarta.validation.Valid;

@Controller
public class RegistrationController {
    @Autowired
    private UserAccountsDetailsService userAccountsDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/aboutUs")
    public String handleAboutUs(){
        return "aboutUs";
    }
    
    @GetMapping("/login")
    public String handleLogin(){
        return "login";
    }

    @GetMapping("/signup")
    public String signUpPage(Model model) {
        model.addAttribute("ua", new SignupRequestDTO()); 
            return "signup";
    }

    @PostMapping("/signup")
    public String createUserAccount(@ModelAttribute @Valid SignupRequestDTO signupRequestDTO, Model model ) throws Exception{
        signupRequestDTO.setPassword(passwordEncoder.encode(signupRequestDTO.getPassword()));
        userAccountsDetailsService.save(signupRequestDTO);
            return "index";
    }
}
