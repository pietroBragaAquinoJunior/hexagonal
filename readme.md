# Spring Boot Rest API

### 🛠️ Technologies

- [x] **JPA**
- [x] **H2**
- [x] **Hexagonal Architecture**
- [x] **Flyway for Migrations**
- [x] **Junit & Mockito for Tests**
- [x] **Global Handler for Exceptions**

---

### Author's Comments:

```java
💬
System.out.println(" This project is one of the bests to show my knowledge and skills in building" +
                   " robust backend applications with clean architecture! ")
```

```java
💬
System.out.println(" This project discusses the **N+1 problem** and demonstrates how to avoid it" +
                   " by correctly utilizing **Join Fetch** for data retrieval. ")
```

### Images 📸

### Ports and Adapters (Hexagonal) Architecture
<img width="2774" height="1582" alt="image" src="https://github.com/user-attachments/assets/ba70620f-339d-444b-96d5-c37e809aa28e" />

---
### Global Exception Handling with ApiError Class
```java

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> details = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            details.put(error.getField(), error.getDefaultMessage()));

        ApiError apiError = new ApiError(
            HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST.getReasonPhrase(),
            "Campos inválidos na requisição.",
            request.getDescription(false).substring(4),
            details
        );
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ApiError> handleRecursoNaoEncontradoException(RecursoNaoEncontradoException ex, WebRequest request) {
        ApiError apiError = new ApiError(
            HttpStatus.NOT_FOUND.value(),
            HttpStatus.NOT_FOUND.getReasonPhrase(),
            ex.getMessage(),
            request.getDescription(false).substring(4)
        );
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private Map<String, String> details;

    public ApiError(int status, String error, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public ApiError(int status, String error, String message, String path, Map<String, String> details) {
        this(status, error, message, path);
        this.details = details;
    }
}

```



---
### Database Migrations using Flyway
<img width="1154" height="286" alt="image" src="https://github.com/user-attachments/assets/1f8f223a-e8e5-4720-84b5-8246f718be1e" />

---
### Professional Mapping using MapStruct
<img width="1131" height="657" alt="image" src="https://github.com/user-attachments/assets/55db70a6-3226-42dc-b8c4-1d762bab4f0b" />

---
### Solving the N+1 Problem using Join Fetch before Business Logic
<img width="988" height="434" alt="image" src="https://github.com/user-attachments/assets/23e73f13-b188-42dc-86e3-b150a9e6f14e" />

---





