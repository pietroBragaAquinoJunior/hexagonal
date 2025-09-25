package com.pietro.hexagonal.core.services;

import com.pietro.hexagonal.core.domain.PessoaDomain;
import com.pietro.hexagonal.core.domain.PontuacaoDomain;
import com.pietro.hexagonal.core.domain.exceptions.RecursoNaoEncontradoException;
import com.pietro.hexagonal.core.ports.PessoaPersistencePort;
import com.pietro.hexagonal.core.ports.PessoaServicePort;

import java.util.List;
import java.util.UUID;

/**
 *
 * Essa classe precisaria do @Service do Lombok, mas como está dentro do CORE da aplicação precisamos criar o Bean manualmente.
 * Faremos essa configuração em um Adapter de Configuração Específico.
 *
 **/

public class PessoaServicePortImpl implements PessoaServicePort {

    private final PessoaPersistencePort pessoaPersistencePort;

    public PessoaServicePortImpl(PessoaPersistencePort pessoaPersistencePort) {
        this.pessoaPersistencePort = pessoaPersistencePort;
    }

    @Override
    public PessoaDomain savePessoa(PessoaDomain pessoaDomain) {
        return pessoaPersistencePort.savePessoa(pessoaDomain);
    }

    @Override
    public List<PessoaDomain> findAll() {
        return pessoaPersistencePort.findAll();
    }

    @Override
    public PontuacaoDomain calcularPontuacao(UUID pessoaId) {
        PessoaDomain pessoaDomain = pessoaPersistencePort.findByIdWithLivros(pessoaId);

        Integer pontuacao = 0;

        if(pessoaDomain.getLivros().isEmpty()){
            throw new RecursoNaoEncontradoException("Essa pessoa não possui livros.");
        }

        pontuacao = pessoaDomain.getLivros().size();

        return new PontuacaoDomain(pontuacao);
    }
}
