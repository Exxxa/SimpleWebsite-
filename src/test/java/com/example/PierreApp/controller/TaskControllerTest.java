package com.example.PierreApp.controller;

import com.example.PierreApp.model.Task;
import com.example.PierreApp.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
    }

    @Test
    void testGetAllTasks() throws Exception {
        // Mock the behavior of the taskService
        when(taskService.getAllTasks()).thenReturn(Collections.emptyList());

        // Perform the GET request
        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(view().name("task-list"))
                .andExpect(model().attributeExists("tasks"));

        // Verify that the taskService's getAllTasks method was called
        verify(taskService, times(1)).getAllTasks();
    }

    @Test
    void testGetTaskById() throws Exception {
        Long taskId = 1L;
        // Mock the behavior of the taskService
        when(taskService.getTaskById(taskId)).thenReturn(Optional.of(new Task()));

        // Perform the GET request
        mockMvc.perform(get("/tasks/{taskId}", taskId))
                .andExpect(status().isOk())
                .andExpect(view().name("task-details"))
                .andExpect(model().attributeExists("task"));

        // Verify that the taskService's getTaskById method was called with the correct argument
        verify(taskService, times(1)).getTaskById(taskId);
    }

     @Test
    void testCreateTask() throws Exception {
        LocalDate dueDate = LocalDate.parse("2024-01-15");
        Task taskToSave = new Task("Test Task", "Description", "Pending", dueDate);

        // Perform the POST request
        mockMvc.perform(get("/tasks/new")
                .param("title", taskToSave.getTitle())
                .param("description", taskToSave.getDescription())
                .param("status", taskToSave.getStatus())
                .param("dueDate", taskToSave.getDueDate().toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/tasks"));

        // Verify that the taskService's createTask method was called with the correct argument
        verify(taskService, times(1)).createTask(any(Task.class));
    }

}
