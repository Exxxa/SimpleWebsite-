// TaskController.java (Controller)

package com.example.PierreApp.controller;

import com.example.PierreApp.model.Task;
import com.example.PierreApp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getAllTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks/task-list";
    }

    @GetMapping("/{taskId}")
    public String getTaskById(@PathVariable Long taskId, Model model) {
        Optional<Task> task = taskService.getTaskById(taskId);
        task.ifPresent(value -> model.addAttribute("task", value));
        return "tasks/task-details";
    }

    @GetMapping("/new")
    public String showTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "tasks/task-form";
    }

    @PostMapping("/new")
    public String createTask(@ModelAttribute Task task) {
        taskService.createTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/{taskId}/edit")
    public String showEditForm(@PathVariable Long taskId, Model model) {
        Optional<Task> task = taskService.getTaskById(taskId);
        task.ifPresent(value -> model.addAttribute("task", value));
        return "tasks/task-edit";
    }

    @PostMapping("/{taskId}/edit")
    public String updateTask(@PathVariable Long taskId, @ModelAttribute Task updatedTask) {
        taskService.updateTask(taskId, updatedTask);
        return "redirect:/tasks";
    }

    @GetMapping("/{taskId}/delete")
    public String deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return "redirect:/tasks";
    }

    @GetMapping("/status/{status}")
    public String getTasksByStatus(@PathVariable String status, Model model) {
        List<Task> tasks = taskService.getTasksByStatus(status);
        model.addAttribute("tasks", tasks);
        return "tasks/task-list";
    }
}
