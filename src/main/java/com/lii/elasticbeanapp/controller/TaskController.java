package com.lii.elasticbeanapp.controller;

import com.lii.elasticbeanapp.model.Task;
import com.lii.elasticbeanapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        return "tasks";
    }

    @GetMapping("/new")
    public String showTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "task-form.html";
    }

    @PostMapping("/save")
    public String saveTask(@ModelAttribute Task task) {
        taskRepository.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        taskRepository.findById(id)
                .ifPresent(task -> model.addAttribute("task", task));
        return "task-form.html";
    }

    @PostMapping("/update/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute Task updatedTask) {
        taskRepository.findById(id).ifPresent(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            taskRepository.save(task);
        });
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return "redirect:/tasks";
    }

    @GetMapping("/")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "index";
    }
}