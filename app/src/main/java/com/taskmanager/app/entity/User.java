package com.taskmanager.app.entity;

import com.taskmanager.app.enums.Role;
import jakarta.persistence.*;
import lombok.Data;


@Entity(name = "Users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer userId;
    private String userName;
    @Enumerated(EnumType.STRING)
    private Role role;
}