package com.taskmanager.app.service.Impl;

import com.taskmanager.app.entity.User;
import com.taskmanager.app.repositry.UserRepo;
import com.taskmanager.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;
    public void registerUser(User user) {
        userRepo.save(user);
    }

    public Optional<User> getUserDetails(Integer userId) {
        return userRepo.findByUserId(userId);
    }
}
