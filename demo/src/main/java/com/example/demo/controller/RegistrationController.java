package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.UserAccounts;
import com.example.demo.repository.UserRepository;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


@GetMapping("/signup")
public String signUpPage(Model model) {
    model.addAttribute("ua", new UserAccounts());
        return "signup";
    }
//fix mapping?
    @PostMapping("/signup")
    public String createUserAccount(@ModelAttribute UserAccounts ua, Model model ){
        ua.setPassword(passwordEncoder.encode(ua.getPassword()));
 userRepository.save(ua);
 return "index";
    }
}
