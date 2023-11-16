package com.group6.cenapp.service;

import com.group6.cenapp.exception.DuplicatedValueException;
import com.group6.cenapp.model.Entity.User;
import com.group6.cenapp.model.dto.UserDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> getAllUsers();

    User saveUser(User user);

    User updateUser(User user);
    void deleteUserById(Integer id);

    Optional<User> getUserById(Integer id);

    @Transactional(readOnly = true)
    Optional<User> getUserById(int id);

    String emailUser(String email);

    @Transactional
    User saveUser(UserDto userDto) throws DuplicatedValueException;

    void deleteById(Long id);

    User update(User user);

    User updatePassword(User user);

    User updateImage(User user);

    User updateRole(User user);

}
