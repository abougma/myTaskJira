package com.example.taskapi.service;

import com.example.taskapi.entity.Priority;
import com.example.taskapi.entity.Task;
import com.example.taskapi.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        taskService = new TaskService(taskRepository);
    }

    @Test
    void shouldCreateTask() {

        //Creation de la tache
        Task task = new Task();
        task.setTitle("Apprendre les tests");
        task.setCompleted(false);
        task.setPriority(Priority.LOW);

        //configuration du comportement du mock
        when(taskRepository.save(any(Task.class)))
                .thenReturn(task);

        //appel du service
        Task result = taskService.createTask(task);
        assertEquals(task, result);

        //verification de l'appel du save
        verify(taskRepository).save(task);
    }

    @Test
    void shouldGetTaskById(){
        Long id = 1L;
        Task task = new Task();
        task.setTitle("Apprendre les get by id");
        task.setPriority(Priority.LOW);
        task.setCompleted(false);

        when(taskRepository.findById(id)).thenReturn(Optional.of(task));

        Task result = taskService.getTaskById(id);

        assertEquals(task, result);

        verify(taskRepository).findById(id);
    }

    @Test
    void shouldGetAllTask(){
        Task task = new Task("Apprendre les get All", false);

        List<Task> tasks = List.of(task);

        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> result = taskService.getAllTasks();

        assertEquals(tasks, result);

        verify(taskRepository).findAll();

    }

    @Test
    void shouldDeleteTask(){
        Long id = 1L;
        Task task = new Task();

        when(taskRepository.findById(id)).thenReturn(Optional.of(task));

        Task result = taskService.deleteTask(id);

        assertEquals(task, result);

        verify(taskRepository).deleteById(id);

    }

    @Test
    void shouldUpdateTask(){
        Long id = 1L;
        Task existingTask = new Task();
        existingTask.setTitle("Apprendre les get by id");

        Task updatedTask = new Task();
        updatedTask.setTitle("Apprendre les update by id");

        when(taskRepository.findById(id)).thenReturn(Optional.of(existingTask));

        Task result = taskService.updateTask(id, updatedTask);

        assertEquals("Apprendre les update by id", result.getTitle());

        verify(taskRepository).save(existingTask);
    }
}