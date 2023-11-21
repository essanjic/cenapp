package com.group6.cenapp.controller;

import com.group6.cenapp.exception.DuplicatedValueException;
import com.group6.cenapp.model.User;
import com.group6.cenapp.model.dto.UserDto;
import com.group6.cenapp.response.ApiResponseHandler;
import com.group6.cenapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/v1/api/users")
public class UserController {


    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable int id) {
        Optional<User> userSearch = userService.getUserById(id);
        if(userSearch.isPresent()){
            return ApiResponseHandler.generateResponse("User data retrieved successfully", HttpStatus.OK, userSearch.get());
        } else {
            return ApiResponseHandler.generateResponseError("User "+ id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody UserDto userDto) throws DuplicatedValueException {
        return ApiResponseHandler.generateResponse("User data save successfully", HttpStatus.OK, userService.saveUser(userDto));
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateUser(@RequestBody UserDto userDto) {
        if(userDto.getId() != null && userService.getUserById(Math.toIntExact(userDto.getId())).isPresent()){
            return ApiResponseHandler.generateResponse("User data update successfully", HttpStatus.OK, userService.updateUser(userDto));
        } else {
            return ApiResponseHandler.generateResponseError("User with ID: "+ userDto.getId() + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id) {
        if(userService.getUserById(id).isPresent()){
            userService.deleteUserById(id);
            return ApiResponseHandler.generateResponse(null, HttpStatus.NO_CONTENT, null);
        } else {
            return ApiResponseHandler.generateResponseError("User "+ id + " not found", HttpStatus.NOT_FOUND);
        }
    }
}
