package com.example.demo;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class EmailNotificationService {

    private final JavaMailSender mailSender;
    private final TaskRepository taskRepository;

    public EmailNotificationService(JavaMailSender mailSender, TaskRepository taskRepository) {
        this.mailSender = mailSender;
        this.taskRepository = taskRepository;
    }

    // Runs every 60 seconds.
    // Sends the notification when the task due date/time has arrived.
    @Scheduled(fixedRate = 60000)
    public void sendDueTaskNotifications() {
        LocalDateTime now = LocalDateTime.now();

        List<Task> tasks = taskRepository.findAll();

        for (Task task : tasks) {
            if (task.getDueDateTime() == null) continue;
            if (task.isCompleted()) continue;
            if (task.isNotificationSent()) continue;
            if (task.getEmail() == null || task.getEmail().isBlank()) continue;

            LocalDateTime due = task.getDueDateTime();

            if (!due.isAfter(now)) {
                sendDueEmail(task);
                task.setNotificationSent(true);
                taskRepository.save(task);
            }
        }
    }

    private void sendDueEmail(Task task) {
        String formatted = task.getDueDateTime()
                .format(DateTimeFormatter.ofPattern("EEEE, MMMM d yyyy 'at' HH:mm"));

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(task.getEmail());
        message.setSubject("⏰ TaskPro: \"" + task.getTitle() + "\" is due now!");

        message.setText(
                "Hi " + getSafeUsername(task) + ",\n\n" +
                "Your task is due now.\n\n" +
                "📌 Task:     " + task.getTitle() + "\n" +
                "🏷 Category: " + task.getCategory() + "\n" +
                "🔺 Priority: " + task.getPriority() + "\n" +
                "🕐 Due:      " + formatted + "\n\n" +
                (task.getNotes() != null && !task.getNotes().isBlank()
                        ? "📝 Notes: " + task.getNotes() + "\n\n"
                        : "") +
                "Stay productive!\n\n" +
                "— TaskPro"
        );

        mailSender.send(message);
    }

    public void sendTaskCreatedConfirmation(Task task) {
        if (task.getEmail() == null || task.getEmail().isBlank()) return;
        if (task.getDueDateTime() == null) return;

        String formatted = task.getDueDateTime()
                .format(DateTimeFormatter.ofPattern("EEEE, MMMM d yyyy 'at' HH:mm"));

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(task.getEmail());
        message.setSubject("✅ TaskPro: Task scheduled — \"" + task.getTitle() + "\"");

        message.setText(
                "Hi " + getSafeUsername(task) + ",\n\n" +
                "Your task was created successfully.\n" +
                "TaskPro will email you when the due date and time arrive.\n\n" +
                "📌 Task:     " + task.getTitle() + "\n" +
                "🏷 Category: " + task.getCategory() + "\n" +
                "🔺 Priority: " + task.getPriority() + "\n" +
                "🕐 Due:      " + formatted + "\n\n" +
                "Good luck!\n\n" +
                "— TaskPro"
        );

        mailSender.send(message);
    }

    private String getSafeUsername(Task task) {
        if (task.getUsername() == null || task.getUsername().isBlank()) {
            return "there";
        }

        return task.getUsername();
    }
}