# ⚙️ NovaGear - E-Commerce Inventory Management API

Welcome to **NovaGear**, a robust, scalable backend REST API for an e-commerce inventory management system. NovaGear is specifically designed to handle dynamic product catalogs, offering seamless management of Products and Categories through a well-structured and documented interface.

---

## 📖 Project Description

NovaGear serves as the backend engine for modern e-commerce platforms. It provides a reliable set of RESTful endpoints to manage inventory data, track product details, and organize them into categorical hierarchies. Built with clean architecture and modern Java frameworks, it ensures high performance, maintainability, and standard-compliant error handling.

---

## 🛠️ Tech Stack

This project leverages a modern Java ecosystem to deliver a production-ready API:

*   **☕ Java:** Core programming language.
*   **🍃 Spring Boot:** Application framework for rapid, auto-configured enterprise development.
*   **🗄️ MySQL:** Relational database for persistent storage.
*   **💾 Spring Data JPA & Hibernate:** ORM abstractions for seamless database interactions.
*   **🗺️ MapStruct:** High-performance, type-safe Java bean mapping.
*   **🌶️ Lombok:** Boilerplate code reduction.
*   **🛡️ Hibernate Validator:** Built-in bean validation annotations (`@Valid`).
*   **🐳 Docker Compose:** Containerized database infrastructure for rapid local setup.
*   **📄 Springdoc OpenAPI (Swagger):** Automated interactive API documentation.

---

## ✨ Key Features

*   **📦 Full CRUD Operations:** Complete Create, Read, Update, and Delete capabilities for both Products and Categories.
*   **🔗 Relational Database Mapping:** Well-structured entity relationships (One-to-Many mapping) linking items to their respective categories.
*   **📑 Advanced Pagination and Sorting:** Efficiently handle large datasets natively through the `/api/v1/products` endpoints.
*   **🛑 Robust Global Exception Handling:** Adheres to **RFC 7807** standard using Spring Boot's `ProblemDetail`. It gracefully catches business exceptions (`ResourceNotFoundException`) and structural validation failures, returning structured JSON error payloads to the client.
*   **🔄 Automatic DTO-to-Entity Mapping:** Secure and clean data transfer using `MapStruct` to decouple database entities from the presentation/API layer.

---

## 🚀 Getting Started

Follow these steps to set up and run the NovaGear API locally on your machine.

### Prerequisites

*   Java 17 or higher
*   Docker and Docker Compose
*   Gradle (or use the included wrapper)

### Setup Instructions

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/your-username/NovaGear.git
    cd NovaGear
    ```

2.  **Start the MySQL Database:**
    You must start the containerized MySQL database environment before running the application.
    ```bash
    docker compose up -d
    ```

3.  **Run the Application:**
    Start the Spring Boot application using the Gradle wrapper or your preferred IDE:
    ```bash
    ./gradlew bootRun
    ```
    *(The API will start locally on port `8080` by default).*

---

## 📡 API Endpoints Overview

Here is a brief summary of the primary RESTful routes available in NovaGear:

### Categories
*   `GET /api/v1/categories` - Retrieve a list of all categories.
*   `GET /api/v1/categories/{id}` - Retrieve a specific category.
*   `POST /api/v1/categories` - Create a new category.
*   `PUT /api/v1/categories/{id}` - Update an existing category.
*   `DELETE /api/v1/categories/{id}` - Delete a category.

### Products
*   `GET /api/v1/products` - Retrieve a paginated and sortable list of products.
*   `GET /api/v1/products/{id}` - Retrieve a specific product.
*   `POST /api/v1/products` - Create a new product.
*   `PUT /api/v1/products/{id}` - Update a product.
*   `DELETE /api/v1/products/{id}` - Delete a product.

### Relational Endpoints
*   `GET /api/v1/products/category/{categoryId}` - Retrieve all products that belong to a specific category.

---

## 🧩 Interactive API Documentation (Swagger)

The NovaGear API is fully documented using OpenAPI standards. Once the application is running, you can explore the endpoints, view schemas, and test requests directly from your browser via the Swagger UI dashboard.

**Access the documentation here:** 
👉 `http://localhost:8080/swagger-ui/index.html`

### UI Previews

<img width="600"  alt="Screencast from 2026-05-29 20-43-44" src="https://github.com/user-attachments/assets/bd7fb2a9-323a-45c1-b934-942385bbc08f" />

*^ Overview of the Swagger UI dashboard and we get all products request from db.*

<img width="600" alt="Screencast from 2026-05-29 20-46-55" src="https://github.com/user-attachments/assets/73c6d6fc-2f45-4d75-948a-4c5ed20af23e" />

*^ we make a request: bring all products with category id 1*

<img width="600" alt="Screencast from 2026-05-29 20-43-44" src="https://github.com/user-attachments/assets/fd424870-5f91-48fa-a6be-3082ce4829ca" />

*^ we make a request: bring products wich category name are laptops*
