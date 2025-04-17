package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.UserAccounts;
import com.example.demo.service.UserAccountsDetailsService;

@Controller
public class RegistrationController {
    @Autowired
    private UserAccountsDetailsService userAccountsDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;


@GetMapping("/signup")
public String signUpPage(Model model) {
    model.addAttribute("ua", new UserAccounts());
        return "signup";
    }

    @PostMapping("/signup")
    public String createUserAccount(@ModelAttribute UserAccounts ua, Model model ) throws Exception{
        ua.setPassword(passwordEncoder.encode(ua.getPassword()));
        userAccountsDetailsService.save(ua);
 return "index";
    }
}
