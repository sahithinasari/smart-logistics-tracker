# Smart Logistics Tracker

A **full-stack logistics management system** built with **Java (Spring Boot)** and **React** that helps vendors, agents, and admins manage and track orders in real time.  
It demonstrates **secure authentication with Keycloak, role-based dashboards, event-driven processing, and scalable backend design**.

---

## Features

### Backend (Spring Boot)
- REST APIs for **order creation, tracking, and updates**
- **Role-based access control (Vendor, Agent, Admin)** via Keycloak (Authorization Code Flow + PKCE)
- **Swagger/OpenAPI** integration for API documentation
- Event-driven processing with **Spring Integration + Batch** for scalable order handling
- **ThreadPoolTaskExecutor** for parallel file polling and batch processing

### Frontend (React + Material UI)
- **Role-specific dashboards**:
    - **Vendor**: Place and manage orders
    - **Agent**: View assigned deliveries and update statuses
    - **Admin**: Monitor system, filter completed/failed orders
- **Secure login flow** using Keycloak (HTTP-only cookies & silent refresh)
- Responsive design with Material UI

---

## Tech Stack

- **Backend**: Java 17, Spring Boot, Spring Security, Spring Data JPA, Spring Integration, Spring Batch
- **Authentication**: Keycloak (Authorization Code Flow with PKCE)
- **Frontend**: React, Material UI, TypeScript
- **Database**: PostgreSQL (configurable)
- **Build Tools**: Maven (Backend), Vite (Frontend)
- **Containerization**: Docker (optional)

---

## Getting Started

### Prerequisites
- Java 17+
- Node.js 18+
- MySQL
- PostgreSQL (optional for keycloak)
- Keycloak 22+ (configured with `vendor`, `agent`, `admin` roles)

### Backend Setup
```bash
cd backend
cp src/main/resources/application-example.yml src/main/resources/application.yml
# Update DB, Keycloak configs
mvn clean install
mvn spring-boot:run
```
Backend runs at http://localhost:2045.

Frontend Setup
```
cd frontend
npm install
npm run dev
```
Frontend runs at http://localhost:3000.

API Documentation
Swagger is available at:
```
 http://localhost:8080/swagger-ui/index.html
```
### Future Enhancements
Real-time order tracking with WebSockets

AI-powered FAQ assistant (via free GPT API)



