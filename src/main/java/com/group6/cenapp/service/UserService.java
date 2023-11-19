package com.group6.cenapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group6.cenapp.exception.DuplicatedValueException;
import com.group6.cenapp.model.Image;
import com.group6.cenapp.model.Role;
import com.group6.cenapp.model.User;
import com.group6.cenapp.model.dto.UserDto;
import com.group6.cenapp.repository.RoleRepository;
import com.group6.cenapp.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor

public class UserService implements UserDetails {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    ObjectMapper mapper;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public List<UserDto> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        List<UserDto> allUsersDto = new ArrayList<>();
        for (User user : allUsers)
            allUsersDto.add(mapper.convertValue(user, UserDto.class));

        return allUsersDto;
    }

    //@Override
    public Optional<User> getUserById(Integer id) { return userRepository.findById(id);}

    public User saveUser(UserDto userDto) throws DuplicatedValueException {

        Optional<User> existUser = userRepository.findByEmail(userDto.getEmail());
        if(existUser.isPresent()) {
            throw new DuplicatedValueException("Este email ya se encuentra en uso");
        }

        Role roleUser = roleRepository.findById(Math.toIntExact(userDto.getIdRole().getId())).get();
        userDto.setIdRole(roleUser);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = mapper.convertValue(userDto, User.class);
        return userRepository.save(user);
    }

    //@Override
    public User updateUser(UserDto userDto) {
        User user = mapper.convertValue(userDto, User.class);
        return userRepository.save(user);
    }

    //@Override
    public void deleteUserById(Integer id){
        Optional<User> userSearch = userRepository.findById(id);
        if(userSearch.isPresent()) {
            userRepository.deleteById(id);
        }
    }



    public int idUser(String email) {
        User user = userRepository.findByEmail(email).get();
        Long id = user.getId();

        return Math.toIntExact((id));
    }

    public String nameUser(String email) {
        User user = userRepository.findByEmail(email).get();
        String nombre = user.getName();

        return (nombre);
    }

    public String lastNameUser(String email) {
        User user = userRepository.findByEmail(email).get();
        String lastName = user.getLastName();
        return (lastName);
    }


    public String emailUser(String email) {
        User user = userRepository.findByEmail(email).get();
        return (user.getEmail());
    }

    public String cityUser(String email){
        User user = userRepository.findByEmail(email).get();
        String cityUser = user.getIdCity().getName();
        String countryUser = user.getIdCity().getIdCountry().getName();
        return (cityUser + ", " + countryUser);
    }

    public String roleUser(String email){
        User user = userRepository.findByEmail(email).get();
        String role = user.getIdRole().getName();
        return (role);
    }

    public Image imageUser(String email){
        User user = userRepository.findByEmail(email).get();
        Image image = user.getImage();
        return (image);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}