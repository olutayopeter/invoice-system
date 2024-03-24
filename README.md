# Invoice System

## Overview:

The Invoice System is a robust Spring Boot application designed to streamline invoice management processes. Leveraging modern web technologies, it provides a comprehensive set of RESTful APIs for generating invoices, facilitating payments, and retrieving detailed invoice information. The system integrates Swagger for API documentation and H2 Console for convenient database access.

## Features
1. Invoice Generation: Create new invoices with specified amounts and customer details.
2. Invoice Payment: Mark invoices as paid once payments are received.
3. Invoice Retrieval: Retrieve invoices by various parameters such as customer email or phone number.
4. Swagger API Documentation: Easily explore and understand the available APIs with Swagger UI integration.
5. H2 Console Access: Seamlessly interact with the underlying H2 in-memory database through the H2 Console.

## Getting started
Prerequisites
1. Java Development Kit (JDK) 17 or higher
2. Maven build tool
3. Git (optional)


## Installation

1. Clone the repository:
   git clone https://github.com/olutayopeter/invoice-system.git
2. Navigate to the project directory:
   cd invoice-system
3. Build and run the application using Maven:
   mvn spring-boot:run
   The application will start on port 8080 by default.
4. Access the H2 Console:
   The H2 Console is enabled with the following configurations:
   URL: http://localhost:8080/h2-console
   JDBC URL: jdbc:h2:mem:invoice_db;DB_CLOSE_ON_EXIT=FALSE
   Username: sa
   Password: (leave blank)
   Use these credentials to access the H2 in-memory database via the H2 Console.
5. Access Swagger Documentation:
   Swagger is integrated for API documentation. You can access the Swagger UI at:
   http://localhost:8080/swagger-ui.html

## Testing
The application includes a suite of JUnit test cases to ensure robustness and reliability. Execute the tests using Maven:
mvn test

## Dependencies
1. Spring Boot: Provides a powerful framework for building and running Spring-based applications.
2. Spring Data JPA: Simplifies data access and manipulation through the JPA specification.
3. Spring Web: Enables the development of web applications and RESTful APIs.
4. Springfox Swagger: Facilitates API documentation with Swagger UI integration.
5. H2 Database: Offers an in-memory database solution for development and testing.
6. Lombok: Reduces boilerplate code and enhances code readability.
7. JUnit: Supports unit testing to ensure code quality and reliability.
