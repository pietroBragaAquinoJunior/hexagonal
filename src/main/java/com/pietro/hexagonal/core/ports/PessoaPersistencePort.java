package com.pietro.hexagonal.core.ports;

import com.pietro.hexagonal.core.domain.PessoaDomain;

import java.util.List;
import java.util.UUID;

// NUNCA VAI TER DTO AQUI, SÓ RETORNA E ENTRA DOMAIN.

public interface PessoaPersistencePort {

    PessoaDomain savePessoa(PessoaDomain pessoaDomain);

    List<PessoaDomain> findAll();

    PessoaDomain findByIdWithLivros(UUID pessoaId);

}
