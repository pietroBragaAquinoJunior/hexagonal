package com.pietro.hexagonal;

import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pietro.hexagonal.core.domain.LivroDomain;
import com.pietro.hexagonal.core.domain.PessoaDomain;
import com.pietro.hexagonal.core.domain.PontuacaoDomain;
import com.pietro.hexagonal.core.domain.ViagemDomain;
import com.pietro.hexagonal.core.ports.PessoaPersistencePort;
import com.pietro.hexagonal.core.services.PessoaServicePortImpl;

import static org.mockito.Mockito.*; 
import static org.junit.jupiter.api.Assertions.*; 

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

        // Alimentando livro ~ pessoa (um lado é Pessoa o outro é Set.) (One to many)
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

        // Alimentando Viagens ~ pessoa (os dois lados são Sets) (Many to many)
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