package com.example.taskapi.controller;

import com.example.taskapi.entity.Category;
import com.example.taskapi.entity.Priority;
import com.example.taskapi.entity.Task;
import com.example.taskapi.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/all")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(
            @PathVariable Long id,
            @RequestBody Task task
    ) {
        return taskService.updateTask(id, task);
    }

    @GetMapping("/category/{category}")
    public List<Task> getTaskCategory(
            @PathVariable Category category
    ) {
        return taskService.getTaskCategory(category);
    }

    @GetMapping("/priority/{priority}")
    public List<Task> getTaskPriority(
            @PathVariable Priority priority
    ) {
        return taskService.getTaskPriority(priority);
    }

    @DeleteMapping("/{id}")
    public Task deleteTask(
            @PathVariable Long id
    ) {
        return taskService.deleteTask(id);
    }


    @GetMapping("/test-sync")
    public String testSync() {
        return "VERSION 1";
    }
}