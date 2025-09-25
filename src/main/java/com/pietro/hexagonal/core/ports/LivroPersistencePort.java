package com.pietro.hexagonal.core.ports;

import com.pietro.hexagonal.core.domain.LivroDomain;

import java.util.List;
import java.util.UUID;

// NUNCA VAI TER DTO AQUI, SÓ RETORNA E ENTRA DOMAIN.

public interface LivroPersistencePort {

    LivroDomain saveLivro(UUID pessoaId, LivroDomain livroDomain);

    List<LivroDomain> findAll();

}
