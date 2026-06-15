<div align="center">

# 🚀 Distributed API Rate Limiter

### Enterprise-Grade API Protection System

<img src="https://img.shields.io/badge/Spring%20Boot-4.0-brightgreen?style=for-the-badge&logo=springboot" />
<img src="https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk" />
<img src="https://img.shields.io/badge/Redis-Distributed-red?style=for-the-badge&logo=redis" />
<img src="https://img.shields.io/badge/MySQL-Database-blue?style=for-the-badge&logo=mysql" />
<img src="https://img.shields.io/badge/React-Frontend-61DAFB?style=for-the-badge&logo=react" />

### ⚡ Secure • Scalable • High Performance • Distributed

</div>

---

# 📖 About The Project

Distributed API Rate Limiter is a production-ready system that protects APIs from abuse, spam requests, brute force attacks, and excessive traffic.

The application uses Redis as a distributed request counter and MySQL for dynamic rate limit configuration management.

This project demonstrates real-world backend engineering concepts used in enterprise-scale systems.

---

# ✨ Core Features

🔐 API Key Based Access Control

⚡ Distributed Rate Limiting Using Redis

📊 Dynamic Rule Management

👥 FREE / PREMIUM / ADMIN User Types

🚫 Automatic Request Blocking

📈 Real-Time Request Tracking

⚠️ HTTP 429 Too Many Requests Handling

📝 Swagger API Documentation

🗄️ MySQL Persistent Configuration Storage

🎨 React Dashboard

📱 Responsive UI

🔄 Dynamic Configuration Updates

---

# 🏗️ System Architecture

```text
                ┌─────────────────────┐
                │     React UI        │
                └──────────┬──────────┘
                           │
                           ▼
                ┌─────────────────────┐
                │ Spring Boot API     │
                └──────────┬──────────┘
                           │
             ┌─────────────┴─────────────┐
             ▼                           ▼

     ┌───────────────┐         ┌────────────────┐
     │ Redis Server  │         │ MySQL Database │
     └───────────────┘         └────────────────┘

             │
             ▼

     ┌─────────────────────┐
     │ Rate Limit Filter   │
     └─────────────────────┘

             │
             ▼

     ┌─────────────────────┐
     │ Protected APIs      │
     └─────────────────────┘
```

---

# 🛠️ Technology Stack

## Backend

☕ Java 21

🌱 Spring Boot

🔐 Spring Security

📦 Spring Data JPA

⚡ Spring Data Redis

📖 Swagger OpenAPI

📄 Lombok

---

## Database

🗄️ MySQL

⚡ Redis

---

## Frontend

⚛️ React JS

🚀 Vite

🔗 Axios

🎨 CSS3

---

# 📂 Project Structure

```text
Distributed-API-Rate-Limiter
│
├── Backend
│   ├── Config
│   ├── Controllers
│   ├── DTOs
│   ├── Entities
│   ├── Filters
│   ├── Repositories
│   ├── Services
│   └── Exception Handling
│
├── Frontend
│   ├── Components
│   ├── Services
│   ├── Dashboard
│   └── API Integration
│
└── Documentation
```

---

# 🚀 API Endpoints

| Method | Endpoint | Description |
|----------|------------|-------------|
| GET | /api/products | Get Products |
| POST | /api/orders | Create Orders |
| GET | /rate-limit/config | View Rules |
| POST | /rate-limit/config | Create Rule |
| PUT | /rate-limit/config/{id} | Update Rule |
| DELETE | /rate-limit/config/{id} | Delete Rule |

---

# 🔥 Example Rate Limit Rule

```json
{
  "endpoint": "/api/products",
  "limit": 5,
  "timeWindowInSeconds": 60,
  "userType": "FREE"
}
```

---

# 📊 Response Headers

```text
X-RateLimit-Limit
X-RateLimit-Remaining
X-RateLimit-Reset
```

---

# 🚫 Rate Limit Exceeded

```json
{
  "status": 429,
  "message": "Too Many Requests. Rate limit exceeded."
}
```

---

# 🎯 Business Benefits

✅ Prevents API Abuse

✅ Protects Backend Resources

✅ Controls Traffic Spikes

✅ Improves System Stability

✅ Enhances Security

✅ Enterprise Ready Architecture

---

# 📈 Future Enhancements

🔑 JWT Authentication

📊 Analytics Dashboard

📧 Email Notifications

📈 Usage Monitoring

🐳 Docker Deployment

☸ Kubernetes Deployment

🔍 API Usage Reports

---

# 📸 Application Screenshots

### Dashboard

> Add your dashboard screenshot here

### Swagger Documentation

> Add Swagger screenshot here

### Postman Testing

> Add Postman screenshot here

---

# 👨‍💻 Developer

## Gauresh Badgujar

### Java Full Stack Developer

📧 Email: gauresh2211@gmail.com

💼 Tech Stack:
Java • Spring Boot • Hibernate • MySQL • Redis • React • REST APIs

---

<div align="center">

# ⭐ If you like this project, don't forget to Star the Repository ⭐

### Made with ❤️ using Spring Boot, Redis, MySQL & React

</div>
