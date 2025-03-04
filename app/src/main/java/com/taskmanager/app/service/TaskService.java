package com.taskmanager.app.service;
import com.taskmanager.app.entity.Task;
import com.taskmanager.app.enums.TaskStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface TaskService {

     void saveTask(Task task);

     Optional<Task> getTask(Integer taskId);

     void updateTask(Integer taskId, Task task);
     void deleteTask(Integer taskId);

     List<Task> getAssignedTasks(Integer userId);

     List<Task> getAssignedTasksOrderdedByPriority(Integer userid);

     List<Task> getAssignedTasksByStatus(TaskStatus status, Integer taskId) ;

     List<Task> getAssignedTasksOrderdedByPriorityLow(Integer userId);

     List<Task> getAssignedTasksOrderdedByDueDateLow(Integer userId);
     List<Task> getAssignedTasksOrderdedByDueDateHigh(Integer userId);

}
