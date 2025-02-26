package com.taskmanager.app.controllers;

import com.taskmanager.app.entitys.Task;
import com.taskmanager.app.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TaskController {
    @Autowired
    TaskService taskService;
    @PostMapping("task")
    public void saveTask(@RequestBody Task task){
        taskService.saveTask(task);
    }
    @GetMapping("tasks/{taskId}")
    public Optional<Task> getTask(@PathVariable Integer taskId){
        return taskService.getTask(taskId);
    }
    @PutMapping("tasks/{taskId}")
    public void updateTask(@PathVariable Integer taskId,@RequestBody Task task){
        taskService.updateTask(taskId,task);
    }
    @DeleteMapping("tasks/{taskId}")
    public void deleteTask(@PathVariable Integer taskId){
        taskService.deleteTask(taskId);
    }


}
