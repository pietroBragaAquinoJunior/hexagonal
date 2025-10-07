# 🧑‍💻 Spring Boot Rest API: Clean Architecture & High Performance

![Ports and Adapters (Hexagonal) Architecture](https://github.com/user-attachments/assets/ba70620f-339d-444b-96d5-c37e809aa28e)

> **💬 Quick Overview:** This project serves as the **definitive template** for building a production-ready API focused on **scalability and maintainability**. It demonstrates a deep understanding of **Clean Architecture**, ensuring the **business logic remains fully isolated and testable**. Crucially, it showcases advanced JPA techniques, leveraging **JOIN FETCH** and optimized **JPQL** to **eliminate the notorious N+1 query problem**, guaranteeing high performance under load.

---

## 🧩 Key Features & Highlights

* **Clean Architecture:** Implements the **Ports and Adapters (Hexagonal) Architecture** for clear separation of concerns, making the core business logic independent of external frameworks and databases.
* **High Performance:** Solves the **N+1 query problem** using JPA `JOIN FETCH` and optimized custom JPQL queries.
* **Robust Error Handling:** Features a **Global Exception Handler** using `@RestControllerAdvice` for centralized and consistent error responses across the API.
* **Data Integrity:** Utilizes **Flyway** for reliable and version-controlled database migrations.
* **Quality Assurance:** Comprehensive use of **JUnit** and **Mockito** for unit and integration testing.
* **Data Mapping:** Professional object mapping is handled by **MapStruct**, ensuring clean and efficient data transfer between layers.

---

## ⚙️ Tech Stack & Practices

| Category | Technologies / Practices |
| :--- | :--- |
| **Core** | **Spring Boot** (Java), **JPA** (Hibernate) |
| **Architecture** | **Ports and Adapters** (Hexagonal), Clean Code |
| **Database** | **H2 Memory Database**, **Flyway** (Migrations) |
| **Testing** | **JUnit** & **Mockito** (Unit/Integration Tests) |
| **Utilities** | **MapStruct** (Data Mapping), **Spring Validation** |
| **Performance** | Optimized JPQL, `JOIN FETCH` (N+1 Solution) |

---

## ⏳ Live Status and Deployment

| Status       | Link |
| :----------- | :--- |
| ✅ finished (no deploy) | -    |

---

## 🛠 Local Installation and Setup
- **Clone the repository**: `git clone https://github.com/pietroBragaAquinoJunior/spring-boot-clean-arch-api/`
- **Navigate to the directory**: `cd spring-boot-clean-arch-api`
- **Build and Run**: Use your preferred IDE (like IntelliJ IDEA) to run the Spring Boot application, or use the Maven/Gradle wrapper:
    - **Maven**: `./mvnw spring-boot:run`
    - **Gradle**: `./gradlew bootRun`
