package com.example.PierreApp.controller;

import com.example.PierreApp.model.Task;
import com.example.PierreApp.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @Test
    void testGetAllTasks() {
        Model model = new BindingAwareModelMap();
        when(taskService.getAllTasks()).thenReturn(Collections.emptyList());

        String viewName = taskController.getAllTasks(model);

        assertEquals("task-list", viewName);
        verify(taskService, times(1)).getAllTasks();
        assertEquals(Collections.emptyList(), model.getAttribute("tasks"));
    }

    // Add similar tests for other methods (getTaskById, showTaskForm, createTask, etc.)

    @Test
    void testShowEditForm() {
        Model model = new BindingAwareModelMap();
        Long taskId = 1L;
        when(taskService.getTaskById(taskId)).thenReturn(Optional.of(new Task()));

        String viewName = taskController.showEditForm(taskId, model);

        assertEquals("task-edit", viewName);
        verify(taskService, times(1)).getTaskById(taskId);
        assertEquals(new Task(), model.getAttribute("task"));
    }

    @Test
    void testUpdateTask() {
        Task updatedTask = new Task();
        updatedTask.setId(1L);

        String viewName = taskController.updateTask(updatedTask);

        assertEquals("redirect:/tasks", viewName);
        verify(taskService, times(1)).updateTask(updatedTask.getId(), updatedTask);
    }

    // Add similar tests for deleteTask, getTasksByStatus, etc.
}
