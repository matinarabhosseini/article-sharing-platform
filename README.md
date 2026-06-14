# Article Sharing Platform

A full-stack article sharing platform developed through multiple phases for the Internet Engineering course.

The project started as a custom web server implementation and gradually evolved into a modern full-stack application with RESTful APIs, React frontend, and relational database persistence.

## Overview

The application allows users to manage and browse articles.

Main features include:

- View article list
- Search articles by title or abstract
- View article details
- Create new articles
- Manage article references
- Sort articles based on reference count
- Store article data persistently in a relational database

## Development Phases

### Phase 1 — Web Server

The first phase focused on implementing a simple web server and serving HTML pages directly.

Main topics:

- HTTP request handling
- HTML response generation
- Article listing page
- Article detail page
- Add article page
- Basic search functionality

### Phase 2 — RESTful API

The second phase changed the application architecture and introduced RESTful API endpoints.

Main topics:

- REST API design
- HTTP methods
- JSON request and response handling
- Article CRUD operations
- Search endpoint design
- Backend separation from frontend

### Phase 3 — React Frontend

The third phase implemented the frontend using React and completed the transition toward a Rich Internet Application architecture.

Main topics:

- React components
- React Router
- Functional components
- Hooks
- State management
- API communication using asynchronous requests
- Client-side rendering

### Phase 4 — Persistence and Relational Database

The fourth phase added persistent data storage using a relational database.

Main topics:

- Relational database design
- Entity modeling
- JPA
- Hibernate ORM
- Spring Data Repository
- Primary keys and foreign keys
- Persistent article and reference storage

## Technologies

- Java
- Spring Boot
- RESTful API
- React
- JavaScript
- HTML
- CSS
- JPA
- Hibernate
- Relational Database
- Maven

## Repository Structure

```text
.
├── docs/
│   ├── phase1-web-server-description.pdf
│   ├── phase2-restful-api-description.pdf
│   ├── phase3-react-description.pdf
│   └── phase4-persistence-db-description.pdf
│
├── phase1-web-server/
├── phase2-restful-api/
├── phase3-react-frontend/
└── phase4-persistence-db/
```

## Academic Context

This project was developed as part of the Internet Engineering course at the University of Tehran.

Each phase extends the previous one and introduces a new architectural concept, starting from low-level web server implementation and ending with a database-backed full-stack web application.

## Notes

This repository is organized for academic documentation and portfolio presentation.
