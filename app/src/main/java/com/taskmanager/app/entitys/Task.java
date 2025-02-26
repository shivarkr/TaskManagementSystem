package com.taskmanager.app.entitys;

import com.taskmanager.app.enums.TaskPriority;
import com.taskmanager.app.enums.TaskStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity(name = "Tasks")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer taskId;
    String title;
    String description;
    TaskStatus status;
    TaskPriority priority;
    Date dueDate;
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
