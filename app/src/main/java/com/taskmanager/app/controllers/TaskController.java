package com.taskmanager.app.controllers;

import com.taskmanager.app.entitys.Task;
import com.taskmanager.app.enums.TaskStatus;
import com.taskmanager.app.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    @GetMapping("assigned/{userId}")
    public List<Task> getAssignedTasks(@PathVariable Integer userId){
        return taskService.getAssignedTasks(userId);
    }
    @GetMapping("assigned/priorityHigh/{userId}")
    public List<Task> getAssignedTasksOrderByPriority(@PathVariable Integer userId){
        return taskService.getAssignedTasksOrderdedByPriority(userId);
    }
    @GetMapping("tasks/{status}/{taskId}")
    public List<Task> getAssignedTasksByStatus(@PathVariable TaskStatus status, @PathVariable Integer taskId){
        return taskService.getAssignedTasksByStatus(status,taskId);
    }
//    @GetMapping("assigned/priorityLow/{userId}")
//    public List<Task> getAssignedTasksOrderByPriorityLow(@PathVariable Integer userId){
//        return taskService.getAssignedTasksOrderdedByPriorityLow(userId);
//    }
//    @GetMapping("assigned/dueDateLow/{userId}")
//    public List<Task> getAssignedTasksOrderByDueDateLow(@PathVariable Integer userId){
//        return taskService.getAssignedTasksOrderdedByDueDateLow(userId);
//    }
//    @GetMapping("assigned/dueDateHigh/{userId}")
//    public List<Task> getAssignedTasksOrderByDueDateHigh(@PathVariable Integer userId){
//        return taskService.getAssignedTasksOrderdedByDueDateHigh(userId);
//    }

}
