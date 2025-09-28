### Spring Boot Rest API: Clean Architecture & High Performance ⭐⭐⭐⭐⭐

🔗 [Link for Source Code](https://github.com/pietroBragaAquinoJunior/hexagonal)

⚙️ **Technologies & Practices**:
- Frameworks: Spring Boot (Java), JPA, H2 Memory Database.
- Architecture: Ports and Adapters (Hexagonal) Architecture, Clean Code Principles.
- DevOps/Data: Flyway for Database Migrations.
- Quality: Junit & Mockito for Unit/Integration Tests.
- Robustness: Global Exception Handlers, Spring Validation.

✨ **Why This Project Matters**

This project serves as the **definitive template** for building a production-ready API focused on **scalability and maintainability**.
It demonstrates a deep understanding of **Clean Architecture**, ensuring the **business logic remains fully isolated and testable**.
Crucially, it showcases advanced JPA techniques, leveraging JOIN FETCH and optimized JPQL to **eliminate the notorious N+1 query problem**, guaranteeing high performance under load.

### Ports and Adapters (Hexagonal) Architecture
<img width="2774" height="1582" alt="image" src="https://github.com/user-attachments/assets/ba70620f-339d-444b-96d5-c37e809aa28e" />

---
### Global Exception Handling for Exceptions
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
```

---
### Database Migrations using Flyway
```java
CREATE TABLE livro (
    id UUID DEFAULT random_uuid() PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    pessoa_id UUID NOT NULL, 
    FOREIGN KEY (pessoa_id) REFERENCES pessoa (id) 
);

INSERT INTO livro (titulo, pessoa_id) VALUES ('Livro legal', '07f86fcd-1d50-40f6-b102-4114c263306b');
INSERT INTO livro (titulo, pessoa_id) VALUES ('Livro legal', '1955f7c7-67d4-4dc6-a63a-364287511c0d');
INSERT INTO livro (titulo, pessoa_id) VALUES ('Livro legal', 'b05d87a4-6a63-4849-a18d-42f410b720be');
INSERT INTO livro (titulo, pessoa_id) VALUES ('Meu Malvado Favorito', '07f86fcd-1d50-40f6-b102-4114c263306b');
INSERT INTO livro (titulo, pessoa_id) VALUES ('Meu Malvado Favorito', '1955f7c7-67d4-4dc6-a63a-364287511c0d');
INSERT INTO livro (titulo, pessoa_id) VALUES ('Meu Malvado Favorito', '6cd7b268-7df0-40de-9779-bba26892b6b2');
INSERT INTO livro (titulo, pessoa_id) VALUES ('Pequeno Principe', '07f86fcd-1d50-40f6-b102-4114c263306b');
INSERT INTO livro (titulo, pessoa_id) VALUES ('Pequeno Principe', 'b05d87a4-6a63-4849-a18d-42f410b720be');
INSERT INTO livro (titulo, pessoa_id) VALUES ('Maquiavel', '07f86fcd-1d50-40f6-b102-4114c263306b');
INSERT INTO livro (titulo, pessoa_id) VALUES ('Maquiavel', '1955f7c7-67d4-4dc6-a63a-364287511c0d');
```

---
### Professional Mapping using MapStruct
```java
@Mapper(componentModel = "spring")
public interface PessoaMapper {
    // Response - Domain
    PessoaResponseDto toPessoaResponseDto(PessoaDomain pessoaDomain);
    @Mapping(target = "id", ignore = true )
    @Mapping(target = "cpf", ignore = true )
    @Mapping(target = "livros", ignore = true )
     @Mapping(target = "viagens", ignore = true )
    PessoaDomain toPessoaDomain(PessoaResponseDto pessoaResponseDto);
    // Request - Domain
    PessoaRequestDto toPessoaRequestDto(PessoaDomain pessoaDomain);
    @Mapping(target = "id", ignore = true )
    @Mapping(target = "livros", ignore = true )
    @Mapping(target = "viagens", ignore = true )
    PessoaDomain toPessoaDomain(PessoaRequestDto pessoaRequestDto);
    // Entity - Domain
    @Mapping(target = "livros", ignore = true )
    PessoaEntity toPessoaEntity(PessoaDomain pessoaDomain);
    PessoaDomain toPessoaDomain(PessoaEntity pessoaEntity);
}
```

---
### Solving the N+1 Problem using Join Fetch before Business Logic
```java
public interface PessoaJpaRepository extends JpaRepository<PessoaEntity, UUID> {

    @Query("select p from PessoaEntity p join fetch p.livros where p.id = :pessoaId")
    Optional<PessoaEntity> findByIdWithLivros(UUID pessoaId);

    @Query("select p from PessoaEntity p join fetch p.viagens where p.id = :pessoaId")
    Optional<PessoaEntity> findByIdWithViagens(UUID pessoaId);

}
```

---
### Spring Validator
```java
@Getter
@Setter
@NoArgsConstructor
public class PessoaRequestDto {
    @NotBlank(message = "O Nome da Pessoa é obrigatório.")
    private String nome;
    @NotBlank(message = "O Email da Pessoa é obrigatório.")
    @Email(message = "Email inválido")
    private String email;
    @NotBlank(message = "O Cpf da Pessoa é obrigatório.")
    @Size(min = 11, max = 11, message = "O Cpf da Pessoa deve ter 11 dígitos.")
    private String cpf;
}

```

---
### Junit & Mockito for Tests
```java
@ExtendWith(MockitoExtension.class)
class PessoaServiceImplTest {

    @Mock
    private PessoaPersistencePort pessoaPersistencePort;

    @InjectMocks
    private PessoaServicePortImpl pessoaService;

    private UUID pessoaId;
    private PessoaDomain pessoaMock;

    private LivroDomain livro1;
    private LivroDomain livro2;

    // Criando a pessoa
    @BeforeEach
    void setUp() {
        pessoaId = UUID.randomUUID();
        pessoaMock = new PessoaDomain();
        pessoaMock.setId(pessoaId);
    }

    @Test
    void calcularPontuacaoLivros_DeveSomarCorretamente() {
        livro1 = new LivroDomain(UUID.randomUUID(), "O Senhor dos Anéis", pessoaMock);
        livro2 = new LivroDomain(UUID.randomUUID(), "Maquiavel", pessoaMock);
        pessoaMock.setLivros(Set.of(livro1, livro2)); 

        when(pessoaPersistencePort.findByIdWithLivros(pessoaId)).thenReturn(pessoaMock);
        int pontuacaoEsperada = 2; 
        PontuacaoDomain resultado = pessoaService.calcularPontuacao(pessoaId); 
        assertNotNull(resultado, "O resultado não deve ser nulo.");
        assertEquals(pontuacaoEsperada, resultado.getPontuacao(), "A pontuação deve ser a contagem de livros (2)."); 
        verify(pessoaPersistencePort, times(1)).findByIdWithLivros(pessoaId);
    }

    @Test
    void calcularPontuacaoViagem_DeveRetornarPontuacaoCorreta() {
        Set<PessoaDomain> pessoasDaViagem = Set.of(pessoaMock);
        ViagemDomain viagem1 = new ViagemDomain(UUID.randomUUID(), "Santa Luzia", pessoasDaViagem );
        ViagemDomain viagem2 = new ViagemDomain(UUID.randomUUID(), "Bacabal", pessoasDaViagem );
        ViagemDomain viagem3 = new ViagemDomain(UUID.randomUUID(), "Alagoas", pessoasDaViagem );
        pessoaMock.setViagens(Set.of(viagem1, viagem2, viagem3));

        when(pessoaPersistencePort.findByIdWithViagens(pessoaId)).thenReturn(pessoaMock);
        int pontuacaoEsperada = 3;
        PontuacaoDomain resultado = pessoaService.calcularPontuacaoViagem(pessoaId); 
        assertNotNull(resultado);
        assertEquals(pontuacaoEsperada, resultado.getPontuacao(), "A pontuação deve ser a esperada para 1 viagem.");
        verify(pessoaPersistencePort, times(1)).findByIdWithViagens(pessoaId);
    }
}
```





