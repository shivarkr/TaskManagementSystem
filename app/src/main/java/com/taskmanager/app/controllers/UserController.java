package com.taskmanager.app.controllers;

import com.taskmanager.app.entity.User;
import com.taskmanager.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("user")
    public void registerUser(@RequestBody User user){
        userService.registerUser(user);
    }
    @GetMapping("user/details/{id}")
    public Optional<User> getUserDetails(@PathVariable Integer userId){
        return  userService.getUserDetails(userId);
    }

}
