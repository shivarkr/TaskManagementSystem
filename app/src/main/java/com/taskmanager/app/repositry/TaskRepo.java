package com.taskmanager.app.repositry;

import com.taskmanager.app.entitys.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends JpaRepository< Task,Integer> {
}
