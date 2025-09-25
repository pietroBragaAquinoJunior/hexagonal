package com.pietro.hexagonal.core.ports;

import com.pietro.hexagonal.core.domain.PaginacaoRequest;
import com.pietro.hexagonal.core.domain.PessoaDomain;

import java.util.List;

/**
 *
 * Não Recebe Pageable, pois o Pageable faz parte do Framework.
 *
 **/


public interface PessoaServicePort {

    PessoaDomain savePessoa(PessoaDomain pessoaDomain);

    List<PessoaDomain> findAll();

}
