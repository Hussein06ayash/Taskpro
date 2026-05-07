package com.example.demo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private boolean completed = false;
    private String priority = "MEDIUM";
    private String category = "General";
    private String notes = "";
    private String username;
    private String email;
    private LocalDateTime dueDateTime;
    private boolean notificationSent = false;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public String getUsername() { return username; }
public void setUsername(String username) { this.username = username; }

public String getEmail() { return email; }
public void setEmail(String email) { this.email = email; }

public LocalDateTime getDueDateTime() { return dueDateTime; }
public void setDueDateTime(LocalDateTime dueDateTime) { this.dueDateTime = dueDateTime; }

public boolean isNotificationSent() { return notificationSent; }
public void setNotificationSent(boolean notificationSent) { this.notificationSent = notificationSent; }
}