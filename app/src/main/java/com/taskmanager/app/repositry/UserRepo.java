package com.taskmanager.app.repositry;

import com.taskmanager.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {
    Optional<User> findByUserName(String email);
    Optional<User> findByUserId(Integer userId);
}
