package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role addRole(String role, Long u_id){
        Role r = new Role();
        r.setId(u_id);
        r.setName(role);
        return roleRepository.save(r);
    }

    public Role findByName(String name) throws Exception {
        return roleRepository.findByName(name).orElseThrow();
    }
}
