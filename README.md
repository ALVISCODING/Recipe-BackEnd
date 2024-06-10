# Recipe Management System

A Recipe Management System built using Java and Spring Boot, designed to store and manage your favorite recipes.

## Features

- **CRUD Operations**: Create, read, update, and delete recipes.
- **Secure Access**: Spring Boot Security for API security.
- **In-Memory Database**: H2 database for storing recipes.
- **REST API**: Expose RESTful endpoints for recipe interactions.
- **Date and Time Handling**: Manage timestamps with LocalDateTime.
- **Simplified Code**: Reduce boilerplate with Project Lombok.

## Technologies

- **Language**: Java
- **Framework**: Spring Boot
- **Security**: Spring Boot Security
- **Database**: H2 Database
- **Data Format**: JSON
- **API**: REST API
- **Date and Time**: LocalDateTime
- **Code Simplification**: Project Lombok

## Getting Started

### Prerequisites

- JDK 8 or higher
- Gradle

### Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/your-username/recipe-management-system.git
    ```

2. Navigate to the project directory:
    ```bash
    cd recipe-management-system
    ```

3. Build the project:
    ```bash
    ./gradlew build
    ```

4. Run the application:
    ```bash
    ./gradlew bootRun
    ```

### Access

- H2 console: `http://localhost:8080/h2-console`
- API endpoints: `http://localhost:8080/api/recipes`

## API Endpoints

### RecipeController
    POST /api/recipe/new: Create a new recipe.
    GET /api/recipe/{id}: Get a recipe by ID.
    GET /api/recipe/search: Search recipes by category or name.
    PUT /api/recipe/{id}: Update an existing recipe.
    DELETE /api/recipe/{id}: Delete a recipe by ID.

### UserController

    POST /api/register: Register a new user.

    ## Security

Security configuration is provided by the `SecurityConfig` class, where endpoints `/api/register` and `/actuator/shutdown` are configured to be accessible without authentication.

Configure security settings in `application.properties`.

## Database

Configure H2 database settings in `application.properties`.

## Project Lombok

Ensure Lombok plugin is installed in your IDE.

## Contributing

Fork the repository and create a pull request with your changes.


## Acknowledgements

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Project Lombok](https://projectlombok.org/)
- [H2 Database](http://www.h2database.com/html/main.html)

