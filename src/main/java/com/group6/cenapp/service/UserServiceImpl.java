package com.group6.cenapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group6.cenapp.exception.DuplicatedValueException;
import com.group6.cenapp.model.Entity.Role;
import com.group6.cenapp.model.Entity.User;
import com.group6.cenapp.model.dto.UserDto;
import com.group6.cenapp.repository.RoleRepository;
import com.group6.cenapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    ObjectMapper mapper;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        List<UserDto> allUsersDto = new ArrayList<>();
        for (User user : allUsers)
            allUsersDto.add(mapper.convertValue(user, UserDto.class));

        return allUsersDto;
    }

    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public void deleteUserById(Integer id) {

    }

    @Override
    public Optional<User> getUserById(Integer id) { return userRepository.findById(id);}

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserById(int id) {
        { return userRepository.findById((Integer) id);}
    }

    @Override
    public String emailUser(String email) {
        User user = userRepository.findByEmail(email).get();
        return (user.getEmail());
    }

    @Transactional
    @Override
    public User saveUser(UserDto userDto) throws DuplicatedValueException {

        Optional<User> existUser = userRepository.findByEmail(userDto.getEmail());
        if(existUser.isPresent()) {
            throw new DuplicatedValueException("Este email ya se encuentra en uso");
        }

        Role roleUser = roleRepository.findById(userDto.getRole().getId()).get();
        userDto.setRole(roleUser);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = mapper.convertValue(userDto, User.class);
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User updatePassword(User user) {
        return null;
    }

    @Override
    public User updateImage(User user) {
        return null;
    }

    @Override
    public User updateRole(User user) {
        return null;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).get();
        String role = user.getRole().getName();


        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(role));

        return new org.springframework.security.core.userdetails.User(email, user.getPassword(), true, true, true, true, authorities);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }


}
