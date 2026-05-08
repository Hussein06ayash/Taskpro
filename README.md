# 📝 TaskPro

A full-stack task manager built with **Spring Boot**, **Java**, and **Vanilla JavaScript**.  
Add tasks, set due dates, and receive **automatic email notifications** when deadlines arrive.

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3-green?style=flat-square&logo=springboot)
![HTML](https://img.shields.io/badge/Frontend-HTML%2FCSS%2FJS-blue?style=flat-square&logo=html5)
![H2](https://img.shields.io/badge/Database-H2-lightgrey?style=flat-square)

---

## ✨ Features

- ✅ **Task Management** — Add, complete, and delete tasks with priority levels and categories
- 📧 **Email Notifications** — Get a confirmation email when you add a task, and a reminder email when it's due
- 👤 **User Login Screen** — Enter your name and email once, it's remembered in the browser
- 📅 **Calendar View** — See your tasks on a monthly calendar with due dates highlighted
- 📓 **Notes** — Create coloured sticky notes
- ⏱ **Pomodoro Timer** — Built-in focus timer with work and break sessions
- 📊 **Analytics** — Charts showing task breakdown by priority and category
- 🌙 **Dark Mode** — Toggle between light and dark themes

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-----------|
| Backend | Java 17 + Spring Boot 3 |
| Database | H2 (in-memory) |
| ORM | Spring Data JPA / Hibernate |
| Frontend | HTML + CSS + Vanilla JavaScript |
| Email | Spring Mail + Gmail SMTP |
| Build | Maven |

---

## ⚙️ Prerequisites

Before running the app, make sure you have:

- **Java 17+** — [Download from Adoptium](https://adoptium.net)
- **Maven** — comes included with the project as `mvnw`
- **A Gmail account** with an App Password

Check your Java version:
```bash
java -version
```

---

## 🚀 How to Run

### 1. Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/taskpro.git
cd taskpro
```

### 2. Configure your email

Open `src/main/resources/application.properties` and fill in your Gmail details:

```properties
spring.mail.username=YOUR_GMAIL@gmail.com
spring.mail.password=YOUR_16_CHARACTER_APP_PASSWORD
```

### 3. Run the app

```bash
.\mvnw spring-boot:run
```
wait until you see : Started DemoApplication in 3.2 seconds
### 4. Open in browser
http://localhost:9090
## 📧 Email Setup

TaskPro uses Gmail SMTP. Gmail requires an **App Password** — not your real password.

1. Go to [myaccount.google.com](https://myaccount.google.com)
2. Click **Security** → **2-Step Verification** → **App passwords**
3. Generate a password for TaskPro
4. Paste the 16-character code into `application.properties`

---

## 📁 Project Structure
taskpro/
├── pom.xml
└── src/
└── main/
├── java/com/example/demo/
│   ├── DemoApplication.java
│   ├── Task.java
│   ├── TaskRepository.java
│   ├── TaskService.java
│   ├── TaskController.java
│   ├── Note.java
│   ├── NoteRepository.java
│   ├── NoteController.java
│   └── EmailNotificationService.java
└── resources/
├── application.properties
└── static/
└── index.html

---

## 🔌 API Endpoints

### Tasks

| Method | URL | Description |
|--------|-----|-------------|
| `GET` | `/api/tasks` | Get all tasks |
| `POST` | `/api/tasks` | Create a new task |
| `DELETE` | `/api/tasks/{id}` | Delete a task |
| `PUT` | `/api/tasks/{id}/complete` | Mark a task as complete |

### Notes

| Method | URL | Description |
|--------|-----|-------------|
| `GET` | `/api/notes` | Get all notes |
| `POST` | `/api/notes` | Create a new note |
| `DELETE` | `/api/notes/{id}` | Delete a note |
| `PUT` | `/api/notes/{id}` | Update a note |

---

## 📬 How Email Notifications Work

1. **On task creation** — a confirmation email is sent immediately
2. **On due date** — a reminder email is sent when the task's due date and time arrives

The app checks for due tasks **every 60 seconds** using Spring's `@Scheduled` annotation.

---

## ⚠️ Known Limitations

- **Data resets on restart** — H2 is in-memory, so tasks are lost when the app stops
- **Email delay** — reminders can be delayed up to 60 seconds
- **No user accounts** — tasks are filtered by email address on the frontend

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

*Built with ☕ Java and Spring Boot*