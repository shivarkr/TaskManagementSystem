package com.taskmanager.app.controllers;

import com.taskmanager.app.entitys.User;
import com.taskmanager.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("user")
    public void registerUser(@RequestBody User user){
        userService.registerUser(user);
    }

}
