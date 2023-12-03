package com.group6.cenapp.service;

import com.group6.cenapp.exception.RegisterErrorException;
import com.group6.cenapp.exception.ResourceNotFoundException;
import com.group6.cenapp.model.dto.UserInfoDTO;
import com.group6.cenapp.model.entity.UserInfo;
import com.group6.cenapp.repository.UserInfoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserInfo> userDetail = repository.findByEmail(email);
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + email));
    }

    public UserInfo findByEmail(String email) throws ResourceNotFoundException {
        Optional<UserInfo> entityOptional = repository.findByEmail(email);
        return entityOptional.orElseThrow(() -> new ResourceNotFoundException("Entity not found with email: " + email));
    }

    public String addUser(UserInfo userInfo) throws RegisterErrorException {
        Optional<UserInfo> existingUserByEmail = repository.findByEmail(userInfo.getEmail());
        if (existingUserByEmail.isPresent()) {
            throw new RegisterErrorException("Email already exists.");
        }
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        repository.save(userInfo);

        return "User Added Successfully";
    }
    public String switchUserRole(Integer id) {
        Optional<UserInfo> userDetail = repository.findById(id);
        if (userDetail.isPresent()) {
            UserInfo userInfo = userDetail.get();
            String currentRole = userInfo.getRoles();

            if ("ROLE_USER".equals(currentRole)) {
                userInfo.setRoles("ROLE_ADMIN");
            } else if ("ROLE_ADMIN".equals(currentRole)) {
                userInfo.setRoles("ROLE_USER");
            }
            repository.save(userInfo);
            return "User role switched successfully";
        } else {
            throw new UsernameNotFoundException("User not found " + id);
        }
    }
    public List<UserInfoDTO> findAllUsers() {
        return repository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private UserInfoDTO convertToDto(UserInfo user) {
        return modelMapper.map(user, UserInfoDTO.class);
    }


    public boolean existsRootUser() {
        return repository.existsByRoles("ROLE_ROOT");
    }

    public boolean isEmailAvailable(String email) {
        return repository.isEmailAvailable(email);
    }
}