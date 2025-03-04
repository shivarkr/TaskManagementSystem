package com.taskmanager.app.entity;

import com.taskmanager.app.enums.TaskPriority;
import com.taskmanager.app.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Entity(name = "Tasks")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskId;
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private Date dueDate;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;
}










//Implement CRUD operations for tasks (title, description, status, priority, dueDate).
//@GeneratedValue(strategy = GenerationType.IDENTITY)
//private int taskId;
//
//private String title;
//private String description;
//
//@Enumerated(EnumType.STRING)
//private TaskStatus status;
//
//@Enumerated(EnumType.ORDINAL)
//private TaskPriority priority;
//
//@Temporal(TemporalType.DATE)
//private Date dueDate;
