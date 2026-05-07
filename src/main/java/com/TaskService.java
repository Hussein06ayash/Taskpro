package com.example.demo;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final EmailNotificationService emailNotificationService;

    public TaskService(TaskRepository taskRepository, EmailNotificationService emailNotificationService) {
        this.taskRepository = taskRepository;
        this.emailNotificationService = emailNotificationService;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task createTask(Task task) {
        Task saved = taskRepository.save(task);
        emailNotificationService.sendTaskCreatedConfirmation(saved);
        return saved;
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Task completeTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setCompleted(true);
        return taskRepository.save(task);
    }
}