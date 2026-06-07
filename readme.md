# MockMate Backend 🚀

A production-oriented **Spring Boot 3.x backend** for **MockMate**, an AI-powered career coaching platform.

This project is a backend migration of the original full-stack MockMate application that was initially built using **Next.js**.

**Note**: The backend is in active development and is not yet production-ready. It is intended for learning and demonstration purposes, showcasing best practices in Java and Spring Boot development.

---

## Original Project

The original MockMate project was developed using:

* Next.js 14
* TypeScript
* Tailwind CSS
* Shadcn UI
* Clerk Authentication
* Google Gemini API

### Original Repository

```text
https://github.com/ashishj12/mockmate
```

This repository focuses only on rebuilding the backend using Java and Spring Boot while preserving the existing business logic and frontend behavior.

---

# Tech Stack

### Backend

* Java 21
* Spring Boot 3.x
* Spring Security 6
* Spring Data JPA
* Hibernate
* PostgreSQL (Neon)
* Flyway Migration
* Maven
* Lombok
* Bean Validation
* WebClient
* SLF4J Logging
* Global Exception Handling
* OpenAPI / Swagger

### AI

* Google Gemini API

### Authentication

* Clerk Authentication
* JWT Validation

---

# Architecture

The project follows Clean Architecture principles.

```text
src/main/java/com/mockmate/mockmate_backend

├── ai
├── config
├── constants
├── controller
├── dto
│   ├── request
│   └── response
├── entity
├── exception
├── mapper
├── repository
├── security
├── service
│   └── impl
├── util
├── validation
└── MockmateBackendApplication.java
```

---

# Features

## User Profile

* Profile Management
* User Information Update

## Resume Builder

* Resume Creation
* Resume Update
* Resume Retrieval
* ATS Optimization Support

## ATS Analysis

* Resume Analysis
* Job Description Matching
* AI-based Suggestions

## Cover Letter Generator

* AI Generated Cover Letters
* Cover Letter History
* Delete Cover Letters

## Interview Preparation

* AI Interview Questions
* Assessment Submission
* Interview History
* Improvement Suggestions

## Industry Insights

* Market Trends
* Growth Analysis
* Skill Recommendations

## AI Integration

* Google Gemini API
* Prompt Builders
* Structured Responses

---

# Database

PostgreSQL with Flyway migrations.

Main tables:

* users
* resumes
* cover_letters
* ats_analysis
* assessments
* industry_insights
* audit_logs

---

# API Response Structure

Success:

```json
{
  "success": true,
  "message": "Resume created successfully",
  "data": {},
  "timestamp": ""
}
```

Error:

```json
{
  "success": false,
  "message": "Validation failed",
  "errors": [],
  "timestamp": ""
}
```

---

# Environment Variables

Create a `.env` file:

```env
DATABASE_URL=
DATABASE_USERNAME=
DATABASE_PASSWORD=

GEMINI_API_KEY=

CLERK_ISSUER=
CLERK_JWKS_URL=
CLERK_JWT_SECRET=
```

---

# Running Locally

### Clone Repository

```bash
git clone https://github.com/ashishj12/mockmate-backend.git
```

### Install Dependencies

```bash
mvn clean install
```

### Run Application

```bash
mvn spring-boot:run
```

---

# API Documentation

Swagger UI:

```text
http://localhost:8080/swagger-ui.html
```

---

# Development Profile

For easier Postman testing:

```properties
spring.profiles.active=dev
```

Authentication is disabled in development mode.

---

# Production Profile

```properties
spring.profiles.active=prod
```

Uses:

* Clerk JWT Authentication
* Role-Based Authorization

---

# Project Goal

This project was built to strengthen practical knowledge of:

* Java
* Spring Boot
* Spring Security
* REST API Design
* PostgreSQL
* Flyway
* Clean Architecture
* Enterprise Backend Development

---

# Author

**Ashish Kumar**

Backend Developer | Java | Spring Boot | PostgreSQL | Next.js | AI Integration

GitHub:

```text
https://github.com/ashishj12
```
