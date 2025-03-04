package com.taskmanager.app.service;

import com.taskmanager.app.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface UserService {
     void registerUser(User user);
     Optional<User> getUserDetails(Integer userId);
}
