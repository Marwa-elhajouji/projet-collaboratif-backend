# Collaborative Project Backend

This is the backend API for managing users, projects, and tasks in a collaborative application.

## Features

- RESTful API to manage:
  - Users
  - Projects
  - Tasks (with statuses and date ranges)
  - Persistent storage with MySQL
  - Unit tests + code coverage (JaCoCo)
  - Dockerized environment
  - CI/CD pipeline with GitHub Actions

## Technologies

- Java with Spring Boot
- Spring Data JPA (for database interaction)
- REST Controllers
- MySQL
- JaCoCo
- Docker + Docker Compose
- GitHub Actions CI

## Requirements

- Java 17+
- Maven 
- Docker + Docker Compose

## Installation

Clone the repository:

```bash
git clone https://github.com/Marwa-elhajouji/projet-collaboratif-backend.git
cd projet-collaboratif-backend/projet-collaboratif
```

## Running the Application
```bash
./mvnw spring-boot:run
```
By default, the API will run at http://localhost:8080.

## Running with Docker

```bash
docker-compose up --build
```

This will:

- Start the MySQL database on localhost:3306
- Launch the Spring Boot API on localhost:8080
- Automatically initialize the schema (schema.sql) and seed data (data.sql)

## API Endpoints

Example endpoints:

- `GET /api/utilisateurs`
- `POST /api/projets`
- `DELETE /api/taches/{id}`

## Environment Variables

The following environment variables are used (defined in `application.properties`):

- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`

 These variables must be updated according to your local or production environment.

## CI/CD Pipeline – GitHub Actions

A GitHub Actions workflow is available under `.github/workflows/ci.yml`.

It:

- Compiles the project
- Runs all unit tests
- Validates database connectivity with MySQL service
- Can be extended for Docker image build and deployment

## Code Coverage – JaCoCo

This project uses JaCoCo to measure test coverage.

To generate the coverage report:

```bash
mvn clean verify
```

To view the report:

```bash
target/site/jacoco/index.html
```

### Online HTML Report

You can view the full JaCoCo code coverage report online here:  
👉 [View Coverage Report](https://marwa-elhajouji.github.io/projet-collaboratif-backend/coverage/jacoco/index.html)

It includes:
- Missed vs executed instructions
- Covered and uncovered branches
- Detailed per-package and per-class coverage

## Database Schema

Schema defined in schema.sql. It includes:

utilisateur: id, nom, email, mot_de_passe, role_id
role: id, nom
projet: id, nom, description, date_debut, date_fin, utilisateur_id
tache: id, titre, description, date_debut, date_fin, statut, projet_id, utilisateur_id

### Entity Relationships

One utilisateur can have multiple projet and tache
Each projet belongs to one utilisateur (responsable)
Each tache is assigned to one utilisateur and belongs to one projet
Each utilisateur has one role



