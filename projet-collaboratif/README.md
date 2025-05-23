# Collaborative Project Backend

This is the backend API for managing users, projects, and tasks in a collaborative application.

## Features

- RESTful API to manage:
  - Users
  - Projects
  - Tasks (with statuses and date ranges)
- Connects to a database for persistence

## Technologies

- Java with Spring Boot
- Spring Data JPA (for database interaction)
- REST Controllers
- MySQL

## Requirements

- Java 17+
- Maven 
- A running SQL database (MySQL, PostgreSQL, etc.)

## Installation

Clone the repository:

```bash
git clone 
cd 
```

## Running the Application
```bash
./mvnw spring-boot:run
```
By default, the API will run at http://localhost:8080.


## API Endpoints

Example endpoints:

GET /api/utilisateurs

POST /api/projets

DELETE /api/taches/{id}

