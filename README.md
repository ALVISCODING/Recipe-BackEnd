Recipe Management System

This project is a Recipe Management System designed to store and manage your favorite recipes. The system is built using Java and Spring Boot, incorporating various backend development concepts such as JSON, REST API, Spring Boot Security, H2 database, LocalDateTime, and Project Lombok.
Features

    CRUD Operations: Create, read, update, and delete recipes.
    Secure Access: Implement Spring Boot Security for secure access to the API.
    In-Memory Database: Utilize H2 database for storing recipes.
    REST API: Expose RESTful endpoints for interacting with recipes.
    Date and Time Handling: Use LocalDateTime for managing timestamps.
    Simplified Code: Use Project Lombok to reduce boilerplate code.

Technologies Used

    Java: Programming language for the application.
    Spring Boot: Framework for building the application.
    Spring Boot Security: For securing the application.
    H2 Database: In-memory database for development and testing.
    JSON: For data exchange.
    REST API: For creating a RESTful service.
    LocalDateTime: For handling date and time.
    Project Lombok: For reducing boilerplate code.

    Getting Started
Prerequisites

    JDK 8 or higher
    Gradle

Installation

    Clone the repository:

    bash

git clone https://github.com/your-username/recipe-management-system.git
cd recipe-management-system

Build the project:

bash

./gradlew build

Run the application:

bash

    ./gradlew bootRun

Access

    H2 console: http://localhost:8080/h2-console
    API endpoints: http://localhost:8080/api/recipes

API Endpoints
RecipeController

    POST /api/recipe/new: Create a new recipe.
    GET /api/recipe/{id}: Get a recipe by ID.
    GET /api/recipe/search: Search recipes by category or name.
    PUT /api/recipe/{id}: Update an existing recipe.
    DELETE /api/recipe/{id}: Delete a recipe by ID.

UserController

    POST /api/register: Register a new user.

Security

Configure security settings in application.properties.
Database

Configure H2 database settings in application.properties.
Project Lombok

Ensure Lombok plugin is installed in your IDE.
