# Movie Reservation System - Backend

This project is the backend of a movie reservation system developed as the final project for the Coding Factory Program of Athens University of Economics and Business. 
It provides a REST API for managing movies, screenings, cinema halls, users, and reservations.

# Database Design
The domain model consists of the following entities:

- User (ADMIN / USER)
- Movie
- Screening
- CinemaHall
- Reservation

The database was designed using a **Model-First** approach, where entities and relationships were defined in the backend and mapped to a MySQL database using JPA/Hibernate.

# Database Setup

The application uses a MySQL database. The database schema is provided in the /db/schema_movie_reservation.sql file. Database credentials are intentionally excluded for security reasons. 

# Architecture
The backend follows a layered architecture: Controller Layer (REST API), Service Layer (Business Logic), Repository Layer (Data Access)
This structure ensures separation of concerns and maintainability.

# Authentication & Authorization
Authentication is implemented using **JSON Web Tokens**. Spring Security is used for securing endpoints. Role-based authorization (ADMIN / USER). Stateless session management

# Technology Stack
 Java, Spring Boot, Spring Security, JWT, MySQL, JPA / Hibernate, Gradle, Swagger (OpenAPI).

The REST API is documented using **Swagger**. After starting the backend application, Swagger UI is available at: http://localhost:8080/swagger-ui.html

# Testing
REST API endpoints were tested using **Postman** (integration testing). Authentication, authorization, and reservation flows were verified through API calls. 
Path: postman/MovieReservationApp.postman_collection.json


# Build and Run instructions
Java 17+, Gradle, MySQL. The backend application runs on http://localhost:8080. The backend is deployed locally for development purposes.

# Frontend Repository:
https://github.com/AthanasiaPet/movie-reservation-react
