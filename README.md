# 🚀 Distributed API Rate Limiter

A professional full-stack Distributed API Rate Limiting System built using Spring Boot, Redis, MySQL, Spring Security, Swagger/OpenAPI, and React.

## 📌 Project Overview

The Distributed API Rate Limiter is designed to protect APIs from abuse by controlling the number of requests that users can make within a specified time window.

The system uses Redis for high-performance distributed request counting and MySQL for storing dynamic rate limit configurations. It supports multiple user types such as FREE, PREMIUM, and ADMIN, allowing different rate limits based on user roles.

## ✨ Features

✅ Distributed API Rate Limiting

✅ Redis-based Request Tracking

✅ MySQL Dynamic Configuration

✅ FREE / PREMIUM / ADMIN User Support

✅ API Key Based Authentication

✅ Dynamic Rate Limit Management

✅ HTTP 429 Too Many Requests Response

✅ Custom Rate Limit Headers

✅ Swagger API Documentation

✅ Spring Security Integration

✅ React Dashboard

✅ Postman Testing Support

✅ Production-Ready Architecture

## 🛠️ Technology Stack

### Backend
- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Spring Data Redis
- MySQL
- Redis
- Lombok
- Swagger OpenAPI

### Frontend
- React JS
- Vite
- Axios
- CSS

### Tools
- Postman
- GitHub
- Docker
- MySQL Workbench
- Eclipse IDE

## 📂 Project Structure

distributed-rate-limiter

├── src/main/java/com/ratelimiter

│ ├── config

│ ├── controller

│ ├── dto

│ ├── entity

│ ├── exception

│ ├── filter

│ ├── repository

│ └── service

│

├── src/main/resources

│ └── application.properties

│

└── frontend

├── src

├── services

└── App.jsx

## ⚙️ Backend Setup

### Step 1: Create Database

```sql
CREATE DATABASE distributed_rate_limiter;
```

### Step 2: Configure MySQL

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/distributed_rate_limiter
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Step 3: Start Redis

```bash
docker run --name redis-rate-limiter -p 6379:6379 -d redis
```

### Step 4: Run Spring Boot Application

```bash
mvn spring-boot:run
```

Application URL:

```text
http://localhost:8080
```

Swagger URL:

```text
http://localhost:8080/swagger-ui.html
```

## 🎨 Frontend Setup

```bash
npm create vite@latest rate-limiter-frontend

cd rate-limiter-frontend

npm install

npm install axios

npm run dev
```

Frontend URL:

```text
http://localhost:5173
```

## 📡 API Endpoints

### Product API

```http
GET /api/products
```

### Order API

```http
POST /api/orders
```

### Get All Rules

```http
GET /rate-limit/config
```

### Create Rule

```http
POST /rate-limit/config
```

### Update Rule

```http
PUT /rate-limit/config/{id}
```

### Delete Rule

```http
DELETE /rate-limit/config/{id}
```

## 🧪 Sample Create Rule Request

```json
{
  "endpoint": "/api/products",
  "limit": 5,
  "timeWindowInSeconds": 60,
  "userType": "FREE"
}
```

## 🔐 Sample Headers

### FREE User

```text
X-API-KEY: gauresh-free-key
X-USER-TYPE: FREE
```

### PREMIUM User

```text
X-API-KEY: gauresh-premium-key
X-USER-TYPE: PREMIUM
```

### ADMIN User

```text
X-API-KEY: admin-user-key
X-USER-TYPE: ADMIN
```

## ✅ Successful Response

```json
[
  {
    "id": 1,
    "name": "Laptop",
    "price": 55000
  },
  {
    "id": 2,
    "name": "Mobile",
    "price": 25000
  }
]
```

## ❌ Rate Limit Exceeded Response

```json
{
  "status": 429,
  "message": "Too Many Requests. Rate limit exceeded."
}
```

## 📊 Rate Limit Headers

```text
X-RateLimit-Limit
X-RateLimit-Remaining
X-RateLimit-Reset
```

## 👥 User Type Limits

| User Type | Endpoint | Limit | Window |
|------------|-----------|--------|---------|
| FREE | /api/products | 5 | 60 sec |
| PREMIUM | /api/products | 100 | 60 sec |
| ADMIN | /api/admin/dashboard | 1000 | 60 sec |

## 🏗️ System Architecture

Client (React Dashboard)

↓

Spring Boot REST API

↓

Rate Limit Filter

↓

Redis Request Counter

↓

MySQL Rule Configuration

↓

Protected APIs

## 🔄 Redis Key Format

```text
rate_limit:user:gauresh-free-key:/api/products:FREE
```

## 📈 Dashboard Capabilities

- Create Rate Limit Rules
- Update Existing Rules
- Delete Rules
- View All Configurations
- Test Protected APIs
- Display Remaining Requests
- Display Reset Time
- Monitor Product API Responses

## 🚀 Future Enhancements

- JWT Authentication
- Role-Based Access Control
- Analytics Dashboard
- Request Monitoring
- Docker Compose Support
- Kubernetes Deployment
- Email Notifications
- API Usage Reports
- Admin Management Panel

## 🎯 Learning Outcomes

- Spring Boot REST API Development
- Redis Integration
- MySQL Integration
- Spring Security
- JPA & Hibernate
- Distributed Systems Concepts
- API Throttling
- Rate Limiting Strategies
- React Frontend Development
- Full Stack Development

## 👨‍💻 Author

Gauresh Badgujar

Java Full Stack Developer

## ⭐ Project Status

✅ Spring Boot Backend Completed

✅ Redis Integration Completed

✅ MySQL Integration Completed

✅ Dynamic Rate Limiting Completed

✅ API Key Authentication Completed

✅ Swagger Documentation Completed

✅ React Dashboard Completed

✅ Postman Testing Completed

✅ Full Stack Project Completed

---

### 🌟 Distributed API Rate Limiter

A robust and scalable rate limiting solution designed to secure APIs, prevent abuse, and ensure fair resource utilization using Spring Boot, Redis, MySQL, and React.
