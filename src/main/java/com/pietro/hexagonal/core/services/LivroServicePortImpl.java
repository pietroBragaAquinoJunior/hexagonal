package com.pietro.hexagonal.core.services;

import java.util.List;
import java.util.UUID;

import com.pietro.hexagonal.core.domain.LivroDomain;
import com.pietro.hexagonal.core.ports.LivroPersistencePort;
import com.pietro.hexagonal.core.ports.LivroServicePort;

/**
 *
 * Essa classe precisaria do @Service do Lombok, mas como está dentro do CORE da aplicação precisamos criar o Bean manualmente.
 * Faremos essa configuração em um Adapter de Configuração Específico.
 *
 **/

public class LivroServicePortImpl implements LivroServicePort {

    private final LivroPersistencePort livroPersistencePort;

    public LivroServicePortImpl(LivroPersistencePort livroPersistencePort) {
        this.livroPersistencePort = livroPersistencePort;
    }

    @Override
    public LivroDomain saveLivro(UUID pessoaId, LivroDomain livroDomain) {
        return livroPersistencePort.saveLivro(pessoaId, livroDomain);
    }

    @Override
    public List<LivroDomain> findAll() {
        return livroPersistencePort.findAll();
    }

}