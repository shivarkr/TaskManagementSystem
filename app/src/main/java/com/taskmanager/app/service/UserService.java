package com.taskmanager.app.service;

import com.taskmanager.app.entitys.User;
import com.taskmanager.app.enums.Role;
import com.taskmanager.app.repositry.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    public void registerUser(User user) {
        userRepo.save(user);
    }

}
