package com.example.PierreApp.service;
// TaskService.java (Service Layer)

import com.example.PierreApp.model.Task;
import com.example.PierreApp.repository.TaskRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long taskId) {
        return taskRepository.findById(taskId);
    }
    @Transactional
    public Task createTask(Task task) {
        // Implement any business logic/validation before saving
        return taskRepository.save(task);
    }

    public Task updateTask(Long taskId, Task updatedTask) {
        // Implement any business logic/validation before updating
        Optional<Task> existingTask = taskRepository.findById(taskId);
        if (existingTask.isPresent()) {
            Task taskToUpdate = existingTask.get();
            taskToUpdate.setTitle(updatedTask.getTitle());
            taskToUpdate.setDescription(updatedTask.getDescription());
            taskToUpdate.setStatus(updatedTask.getStatus());
            taskToUpdate.setDueDate(updatedTask.getDueDate());

            return taskRepository.save(taskToUpdate);
        } else {
            // Handle the case when the task with the given ID is not found
            return null;
        }
    }

    public void deleteTask(Long taskId) {
        // Implement any business logic/validation before deletion
        taskRepository.deleteById(taskId);
    }

    public List<Task> getTasksByStatus(String status) {
        return taskRepository.findByStatus(status);
    }
}
