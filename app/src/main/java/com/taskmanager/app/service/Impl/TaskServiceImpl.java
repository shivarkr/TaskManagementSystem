package com.taskmanager.app.service.Impl;

import com.taskmanager.app.entity.Task;
import com.taskmanager.app.entity.User;
import com.taskmanager.app.enums.Role;
import com.taskmanager.app.enums.TaskStatus;
import com.taskmanager.app.repositry.TaskRepo;
import com.taskmanager.app.repositry.UserRepo;
import com.taskmanager.app.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskRepo taskRepo;

    @Autowired
    UserRepo userRepo;
    //admin
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

    // both admin and user
    private User getCurrentUser() {
        OAuth2User oAuth2User = (OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = (String) oAuth2User.getAttributes().get("email");
        return userRepo.findByUserName(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private boolean isAdmin(User user) {
        return user.getRole().equals(Role.ADMIN);
    }
    private boolean checkIfAccessIsAvailable(Integer userId){
        User currentUser = getCurrentUser();
        return isAdmin(currentUser) || currentUser.getUserId().equals(userId);
    }

    public List<Task> getAssignedTasks(Integer userId) {
        if (checkIfAccessIsAvailable(userId)) {
            return taskRepo.findByUserId1(userId);
        } else {
            throw new AccessDeniedException("You do not have permission to access these tasks.");
        }
    }

    public List<Task> getAssignedTasksOrderdedByPriority(Integer userId) {
        if (checkIfAccessIsAvailable(userId)) {
            return taskRepo.getTasksForUserByPriorityHigh(userId);
        } else {
            throw new AccessDeniedException("You do not have permission to access these tasks.");
        }

    }

    public List<Task> getAssignedTasksByStatus(TaskStatus status, Integer userId) {
        if (checkIfAccessIsAvailable(userId)) {
            return taskRepo.getAssignedTasksByStatus(userId,status);
        } else {
            throw new AccessDeniedException("You do not have permission to access these tasks.");
        }
    }

    public List<Task> getAssignedTasksOrderdedByPriorityLow(Integer userId) {
        if (checkIfAccessIsAvailable(userId)) {
            return taskRepo.getTasksForUserByPriorityLow(userId);
        } else {
            throw new AccessDeniedException("You do not have permission to access these tasks.");
        }
    }

    public List<Task> getAssignedTasksOrderdedByDueDateLow(Integer userId) {
        if (checkIfAccessIsAvailable(userId)) {
            return taskRepo.getTasksForUserByDueDateLow(userId);
        } else {
            throw new AccessDeniedException("You do not have permission to access these tasks.");
        }
    }
    public List<Task> getAssignedTasksOrderdedByDueDateHigh(Integer userId) {
        if (checkIfAccessIsAvailable(userId)) {
            return taskRepo.getTasksForUserByDueDateHigh(userId);
        } else {
            throw new AccessDeniedException("You do not have permission to access these tasks.");
        }
    }
}
