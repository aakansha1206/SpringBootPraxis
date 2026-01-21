# Spring Boot Praxis - POC Project

A Spring Boot proof-of-concept application demonstrating best practices for building RESTful APIs with CRUD operations, validation, and testing.

## Features

- **RESTful API**: Complete CRUD operations for User management
- **Spring Data JPA**: Database operations with Spring Data JPA and H2 in-memory database
- **Validation**: Request validation using Bean Validation API
- **Lombok**: Reduces boilerplate code with annotations
- **Testing**: Comprehensive unit and integration tests
- **H2 Console**: In-memory database console for development

## Technology Stack

- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- H2 Database
- Lombok
- Maven
- JUnit 5
- Mockito

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Getting Started

### Build the Project

```bash
mvn clean install
```

### Run the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### Access H2 Console

Visit `http://localhost:8080/h2-console` with the following credentials:
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (leave empty)

## API Endpoints

### User Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/users` | Get all users |
| GET | `/api/users/{id}` | Get user by ID |
| GET | `/api/users/active` | Get all active users |
| POST | `/api/users` | Create a new user |
| PUT | `/api/users/{id}` | Update an existing user |
| DELETE | `/api/users/{id}` | Delete a user |

### Sample API Requests

#### Create User
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john@example.com",
    "isActive": true
  }'
```

#### Get All Users
```bash
curl http://localhost:8080/api/users
```

#### Get User by ID
```bash
curl http://localhost:8080/api/users/1
```

#### Update User
```bash
curl -X PUT http://localhost:8080/api/users/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Updated",
    "email": "johnupdated@example.com",
    "isActive": true
  }'
```

#### Delete User
```bash
curl -X DELETE http://localhost:8080/api/users/1
```

## Testing

### Run All Tests

```bash
mvn test
```

### Run Specific Test Class

```bash
mvn test -Dtest=UserServiceTest
```

## Project Structure

```
src/
├── main/
│   ├── java/com/example/springbootpraxis/
│   │   ├── SpringBootPraxisApplication.java
│   │   ├── controller/
│   │   │   └── UserController.java
│   │   ├── service/
│   │   │   └── UserService.java
│   │   ├── repository/
│   │   │   └── UserRepository.java
│   │   ├── model/
│   │   │   └── User.java
│   │   └── dto/
│   │       └── UserDTO.java
│   └── resources/
│       ├── application.properties
│       └── application-test.properties
└── test/
    └── java/com/example/springbootpraxis/
        ├── SpringBootPraxisApplicationTests.java
        ├── controller/
        │   └── UserControllerTest.java
        └── service/
            └── UserServiceTest.java
```

## Development

This POC demonstrates:
- Clean architecture with separation of concerns (Controller, Service, Repository layers)
- DTO pattern for data transfer
- Entity validation
- Exception handling
- Unit testing with Mockito
- Integration testing with MockMvc
- In-memory database for development and testing

## License

This project is created as a POC for educational purposes.
