package com.pietro.hexagonal.core.ports;

import com.pietro.hexagonal.core.domain.PessoaDomain;
import com.pietro.hexagonal.core.domain.PontuacaoDomain;

import java.util.List;
import java.util.UUID;

// NUNCA VAI TER DTO AQUI, SÓ RETORNA E ENTRA DOMAIN.

public interface PessoaServicePort {

    PessoaDomain savePessoa(PessoaDomain pessoaDomain);

    List<PessoaDomain> findAll();

    PontuacaoDomain calcularPontuacao(UUID pessoaId);
}
