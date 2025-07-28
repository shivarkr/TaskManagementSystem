package com.taskmanager.app.repositry;

import com.taskmanager.app.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
    User findByUserName(String username);
}
