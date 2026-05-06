# Spring Boot Practice Project

This repository is a personal learning project built to practice and understand the **Spring Boot framework**, including REST APIs, database integration, and authentication using JWT.

The goal of this project is not production deployment, but to build strong foundational knowledge of backend development using Java and Spring Boot.

---

## 🚀 Tech Stack

- Java 23
- Spring Boot 4.0.6
- Spring Web (REST APIs)
- Spring Data JPA (Hibernate)
- PostgreSQL (via Docker)
- Spring Security
- JWT (via jjwt 0.12.6)
- Maven

---

## 📁 Project Structure

This project follows standard Spring Boot architecture:

```
src/main/java/com/zderival/springbootpractice/
│
├── SpringBootPracticeApplication.java   # Entry point — starts the Spring Boot app
│
├── SoftwareEngineer.java                # Entity — maps to the software_engineer table
├── SoftwareEngineerController.java      # Controller — handles HTTP requests/responses
├── SoftwareEngineerService.java         # Service — business logic layer
├── SoftwareEngineerRepository.java      # Repository — talks directly to the database
├── SoftwareEngineerNotFoundException.java # Custom exception for missing engineers
│
├── User.java                            # Entity — maps to the users table
├── UserRepository.java                  # Repository — handles user database operations
│
├── AuthController.java                  # Controller — handles register and login endpoints
├── AuthService.java                     # Service — handles registration and JWT login logic
│
├── JwtUtil.java                         # Utility — generates and validates JWT tokens
├── JwtFilter.java                       # Filter — intercepts requests and validates tokens
├── SecurityConfig.java                  # Configuration — defines security rules and password encoding
│
└── GlobalExceptionHandler.java          # Handles exceptions globally and returns clean error responses
```

---

## 🔗 API Endpoints

### Authentication (Public)
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/auth/register` | Register a new user |
| POST | `/api/v1/auth/login` | Login and receive a JWT token |

### Software Engineers (Protected — requires JWT token)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/software-engineers` | Get all engineers |
| GET | `/api/v1/software-engineers/{id}` | Get engineer by ID |
| POST | `/api/v1/software-engineers` | Add a new engineer |
| PUT | `/api/v1/software-engineers/{id}` | Update an engineer |
| DELETE | `/api/v1/software-engineers/{id}` | Delete an engineer |

---

## 🔐 Authentication Flow

1. Register a user via `POST /api/v1/auth/register`
2. Login via `POST /api/v1/auth/login` — receive a JWT token
3. Include the token in the `Authorization` header for protected requests:
```
Authorization: Bearer <your_token_here>
```

---

## 🐳 Running with Docker

This project uses Docker to run PostgreSQL locally.

### Prerequisites
- Docker Desktop installed and running

### Start the database
```bash
docker compose up -d
```

### Stop the database
```bash
docker compose down
```

---

## 📚 What I Learned

- Building REST APIs with Spring Boot
- Connecting Spring Boot to a PostgreSQL database using Docker
- Using Spring Data JPA to perform CRUD operations without writing SQL
- Implementing JWT authentication with Spring Security
- Layered architecture: Controller → Service → Repository
- Exception handling with `@ControllerAdvice`
- Securing endpoints and hashing passwords with BCrypt


*Built while learning Spring Boot — mistakes were made, errors were debugged, and knowledge was gained.* 💪