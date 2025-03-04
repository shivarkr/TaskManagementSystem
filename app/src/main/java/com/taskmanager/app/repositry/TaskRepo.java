package com.taskmanager.app.repositry;

import com.taskmanager.app.entity.Task;
import com.taskmanager.app.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository< Task,Integer> {
    @Query("select t from Tasks t where t.user.userId = ?1 order by t.dueDate ASC")
    List<Task> findByUserId1(Integer userId);

    @Query("select t from Tasks t where t.user.userId = ?1 order by t.priority DESC")
    List<Task> getTasksForUserByPriorityHigh(Integer userId);

    @Query("select t from Tasks t where t.user.userId = ?1 order by t.priority")
    List<Task> getTasksForUserByPriorityLow(Integer userId);

    @Query("select t from Tasks t where t.user.userId = ?1 order by t.dueDate ASC")
    List<Task> getTasksForUserByDueDateLow(Integer userId);

    @Query("select t from Tasks t where t.user.userId = ?1 order by t.dueDate DESC")
    List<Task> getTasksForUserByDueDateHigh(Integer userId);

    @Query("select t from Tasks t where t.user.userId = ?1 and  t.status =?2")
    List<Task> getAssignedTasksByStatus(Integer userid, TaskStatus status);
}
