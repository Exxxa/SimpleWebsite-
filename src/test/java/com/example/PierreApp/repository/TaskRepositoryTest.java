package com.example.PierreApp.repository;

import com.example.PierreApp.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void testSaveAndFindById() {
        LocalDate dueDate = LocalDate.parse("2024-01-15");
        // Save a task to the database
        Task taskToSave = new Task("Test Task", "Description", "Pending", dueDate);
        Task savedTask = taskRepository.save(taskToSave);

        // Find the task by ID
        Optional<Task> foundTask = taskRepository.findById(savedTask.getId());

        assertTrue(foundTask.isPresent());
        assertEquals(savedTask, foundTask.get());
    }

    @Test
    void testFindByStatus() {
        
        LocalDate dueDate = LocalDate.parse("2024-01-15");
        // Save tasks with different statuses to the database
        taskRepository.save(new Task("Task 1", "Description", "Pending", dueDate));
        taskRepository.save(new Task("Task 2", "Description", "Completed", dueDate));
        taskRepository.save(new Task("Task 3", "Description", "Pending", dueDate));

        // Find tasks by status
        List<Task> pendingTasks = taskRepository.findByStatus("Pending");
        List<Task> completedTasks = taskRepository.findByStatus("Completed");

        assertEquals(2, pendingTasks.size());
        assertEquals(1, completedTasks.size());
    }
    
    // Add more tests for other repository methods as needed
}
