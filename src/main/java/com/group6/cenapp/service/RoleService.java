package com.group6.cenapp.service;


import com.group6.cenapp.model.Role;
import com.group6.cenapp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;


    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }


    public Optional<Role> getRoleById(Integer id) {
        return roleRepository.findById(id);
    }


    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }


    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }


    public void deleteRoleById(Integer id) {
        roleRepository.deleteById(id);
    }
}
