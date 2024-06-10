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

- **POST /api/recipe/new**: Create a new recipe.
    ```java
    @PostMapping("/api/recipe/new")
    public ResponseEntity<?> createRecipe(@Valid @RequestBody Recipe newRecipe) {
        return ResponseEntity.ok().body(recipeService.saveRecipe(newRecipe));
    }
    ```

- **GET /api/recipe/{id}**: Get a recipe by ID.
    ```java
    @GetMapping("/api/recipe/{id}")
    public ResponseEntity<?> getRecipe(@PathVariable("id") long id) {
        Optional<Recipe> recipe = recipeService.getSaveRecipe(id);
        if (recipe.isPresent()) {
            return ResponseEntity.ok(recipe);
        } else {
            return ResponseEntity.notFound().build();
        }
