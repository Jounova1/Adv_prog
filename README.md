# Spring Boot MVC & Repository Architecture

A fully structured Spring Boot application demonstrating the **Model-View-Controller (MVC)** pattern combined with the **Repository design pattern** for clean separation of concerns.

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/example/app/
│   │       ├── SpringBootMvcApplication.java    # Main application entry point
│   │       ├── controller/
│   │       │   └── UserController.java          # REST API endpoints
│   │       ├── service/
│   │       │   └── UserService.java             # Business logic layer
│   │       ├── repository/
│   │       │   └── UserRepository.java          # Data access layer
│   │       ├── entity/
│   │       │   └── User.java                    # JPA entity
│   │       ├── dto/
│   │       │   └── UserDTO.java                 # Data transfer object
│   │       └── config/
│   │           └── (Configuration classes)
│   └── resources/
│       ├── application.properties                # App configuration
│       ├── templates/                            # Thymeleaf templates (if using)
│       └── static/                               # Static files (CSS, JS)
└── test/
    └── java/
        └── com/example/app/
            └── (Unit tests)
```

## Architecture Pattern

### Three-Tier Architecture

1. **Controller Layer** (com.example.app.controller)
   - Handles HTTP requests and responses
   - Routes incoming requests to appropriate service methods
   - Validates input and returns response entities

2. **Service Layer** (com.example.app.service)
   - Contains business logic and rules
   - Orchestrates operations between controllers and repositories
   - Handles transactions and data transformation
   - Converts between DTOs and Entities

3. **Repository Layer** (com.example.app.repository)
   - Interfaces with the database through Spring Data JPA
   - Provides CRUD operations and custom queries
   - Abstraction for data access logic

4. **Entity Layer** (com.example.app.entity)
   - JPA entities representing database tables
   - Contains field mappings and relationships

5. **DTO Layer** (com.example.app.dto)
   - Data Transfer Objects for API communication
   - Provides validation annotations
   - Decouples API contracts from database structure

## Technologies Used

- **Spring Boot 3.2.0** - Framework
- **Spring Data JPA** - ORM and Repository abstraction
- **H2 Database** - In-memory database (for development)
- **Lombok** - Reduce boilerplate code
- **Validation** - Input validation
- **JUnit 5** - Unit testing

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Build the Project

```bash
mvn clean install
```

### Run the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

### User Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/users` | Get all users |
| GET | `/api/users/{id}` | Get user by ID |
| GET | `/api/users/search?term=...` | Search users |
| GET | `/api/users/active/list` | Get active users |
| POST | `/api/users` | Create a new user |
| PUT | `/api/users/{id}` | Update user |
| DELETE | `/api/users/{id}` | Delete user |

### Example Requests

**Create a User:**
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john@example.com",
    "phone": "1234567890"
  }'
```

**Get All Users:**
```bash
curl http://localhost:8080/api/users
```

**Update a User:**
```bash
curl -X PUT http://localhost:8080/api/users/1 \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Jane",
    "lastName": "Doe",
    "email": "jane@example.com",
    "phone": "0987654321"
  }'
```

## Database

The application uses **H2 in-memory database** by default. Access the H2 console at:

```
http://localhost:8080/h2-console
```

**Credentials:**
- URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (leave empty)

### Switch to MySQL (Optional)

1. Uncomment the MySQL dependency in `pom.xml`
2. Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```

## Testing

Run all tests:
```bash
mvn test
```

Unit tests are located in `src/test/java/com/example/app/`

## Key Concepts

### Model (Entity)
- Represents the data structure and database schema
- Annotated with JPA annotations (`@Entity`, `@Table`, etc.)

### View (API Response)
- REST API responses formatted as JSON
- DTOs define the contract between API and clients

### Controller
- Routes HTTP requests to service methods
- Validates input and handles responses
- Maps to URL endpoints

### Repository Pattern
- Data access layer abstraction
- Encapsulates database queries
- Makes switching databases easier

### Service Layer
- Business logic and validation
- Transaction management
- DTO to Entity conversion

## Best Practices Implemented

✅ **Separation of Concerns** - Each layer has distinct responsibilities
✅ **Repository Pattern** - Data access abstraction
✅ **DTOs** - Decoupling API from database models
✅ **Validation** - Input validation at controller and DTO levels
✅ **Exception Handling** - Centralized error handling in controllers
✅ **Transactions** - Service layer transaction management
✅ **Lombok** - Reduced boilerplate code
✅ **SOLID Principles** - Following design best practices
✅ **RESTful API** - Standard HTTP methods and status codes

## Next Steps

1. **Add more entities** - Create additional entities and repositories
2. **Implement authentication** - Add Spring Security
3. **Add logging** - Implement comprehensive logging
4. **Create integration tests** - Add Spring Boot Test integration tests
5. **Implement caching** - Add Spring Cache abstraction
6. **API documentation** - Add Swagger/OpenAPI documentation

## License

This project is open source and available under the MIT License.
