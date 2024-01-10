package com.example.PierreApp.service;

import com.example.PierreApp.model.Task;
import com.example.PierreApp.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void testGetAllTasks() {
        // Mock the behavior of the taskRepository
        when(taskRepository.findAll()).thenReturn(Arrays.asList(new Task(), new Task()));

        // Call the service method
        List<Task> tasks = taskService.getAllTasks();

        // Verify the results
        assertEquals(2, tasks.size());
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void testGetTaskById() {
        Long taskId = 1L;
        // Mock the behavior of the taskRepository
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(new Task()));

        // Call the service method
        Optional<Task> task = taskService.getTaskById(taskId);

        // Verify the results
        assertTrue(task.isPresent());
        verify(taskRepository, times(1)).findById(taskId);
    }

    @Test
    void testCreateTask() {
        
        LocalDate dueDate = LocalDate.parse("2024-01-15");
        // Create a task to save
        Task taskToSave = new Task("Test Task", "Description", "Pending",dueDate);

        // Call the service method
        taskService.createTask(taskToSave);

        // Verify that the taskRepository's save method was called with the correct argument
        verify(taskRepository, times(1)).save(taskToSave);
    }

    @Test
    void testUpdateTask() {
        
        LocalDate dueDate = LocalDate.parse("2024-01-15");
        Long taskId = 1L;
        Task updatedTask = new Task("Updated Task", "Updated Description", "Completed", dueDate);

        // Mock the behavior of the taskRepository
        when(taskRepository.existsById(taskId)).thenReturn(true);

        // Call the service method
        taskService.updateTask(taskId, updatedTask);

        // Verify that the taskRepository's save method was called with the correct argument
        verify(taskRepository, times(1)).save(updatedTask);
    }

    @Test
    void testUpdateTaskNotFound() {
        
        LocalDate dueDate = LocalDate.parse("2024-01-15");
        Long taskId = 1L;
        Task updatedTask = new Task("Updated Task", "Updated Description", "Completed", dueDate);

        // Mock the behavior of the taskRepository
        when(taskRepository.existsById(taskId)).thenReturn(false);

        // Call the service method and expect an exception
        assertThrows(IllegalArgumentException.class, () -> taskService.updateTask(taskId, updatedTask));

        // Verify that the taskRepository's save method was not called
        verify(taskRepository, never()).save(updatedTask);
    }

    @Test
    void testDeleteTask() {
        Long taskId = 1L;

        // Call the service method
        taskService.deleteTask(taskId);

        // Verify that the taskRepository's deleteById method was called with the correct argument
        verify(taskRepository, times(1)).deleteById(taskId);
    }

    @Test
    void testGetTasksByStatus() {
        String status = "Pending";
        // Mock the behavior of the taskRepository
        when(taskRepository.findByStatus(status)).thenReturn(Arrays.asList(new Task(), new Task()));

        // Call the service method
        List<Task> tasks = taskService.getTasksByStatus(status);

        // Verify the results
        assertEquals(2, tasks.size());
        verify(taskRepository, times(1)).findByStatus(status);
    }
}
