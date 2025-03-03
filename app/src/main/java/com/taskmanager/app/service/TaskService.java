package com.taskmanager.app.service;
import com.taskmanager.app.entitys.Task;
import com.taskmanager.app.enums.TaskStatus;
import com.taskmanager.app.repositry.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepo taskRepo;
    public void saveTask(Task task){
        taskRepo.save(task);
    }

    public Optional<Task> getTask(Integer taskId) {
        return taskRepo.findById(taskId);
    }

    public void updateTask(Integer taskId, Task task) {
        Task updatedTask = new Task();
        updatedTask.setTaskId(taskId);
        updatedTask.setDescription(task.getDescription());
        updatedTask.setTitle(task.getTitle());
        updatedTask.setStatus(task.getStatus());
        updatedTask.setDueDate(task.getDueDate());
        updatedTask.setPriority(task.getPriority());
        taskRepo.save(updatedTask);

    }

    public void deleteTask(Integer taskId) {
        taskRepo.deleteById(taskId);
    }

    public List<Task> getAssignedTasks(Integer userId) {
        return taskRepo.findByUserId1(userId);
    }

    public List<Task> getAssignedTasksOrderdedByPriority(Integer userid) {
        return taskRepo.getTasksForUserByPriorityHigh(userid);
    }

    public List<Task> getAssignedTasksByStatus(TaskStatus status, Integer taskId) {
        return taskRepo.getAssignedTasksByStatus(taskId,status);
    }
//
//    public List<Task> getAssignedTasksOrderdedByPriorityLow(Integer userId) {
//        return taskRepo.getTasksForUserByPriorityLow(userId);
//    }
//
//    public List<Task> getAssignedTasksOrderdedByDueDateLow(Integer userId) {
//        return taskRepo.getTasksForUserByDueDateLow(userId);
//    }
//    public List<Task> getAssignedTasksOrderdedByDueDateHigh(Integer userId) {
//        return taskRepo.getTasksForUserByDueDateHigh(userId);
//    }
}
