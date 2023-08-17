# Todo App API


This is a RESTful API backend project for a todo system. The API allows users to create and manage todos.

## Technologies Used
      *  Spring Boot
      *  Java
      *  Maven
      *  Swagger
      *  Hibernate
      *  JPA
      *  MySQL
      *  IntelliJ

## Features
  *  Users can perform CRUD operations for todo
  *  Users can find all todos or find a particular todo based on id

## To get started with the project, follow these steps:


  *  Clone the repository "https://github.com/ViswajithMuraleedharan/Todo_Api"
   * Import the project in IntelliJ as a Maven project
   * Configure the MySQL database credentials in application.properties file
   * Build and run the project

## Endpoints
### Foods
* GET (/find-all): Retrieve all todos
* GET(/find-todo/id/{id}): Retrieve todos based on id
* POST (/add-todo): Create a new todo
* PUT (/update-todo/id/{id}): Update a todo by ID
* DELETE (delete-todo/id/{id}): Delete a todo by ID
