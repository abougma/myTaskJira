package com.example.taskapi.repository;

import com.example.taskapi.entity.Category;
import com.example.taskapi.entity.Priority;
import com.example.taskapi.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCategory(Category category);
    List<Task> findByPriority(Priority priority);
}