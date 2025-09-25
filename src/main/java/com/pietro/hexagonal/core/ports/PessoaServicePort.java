package com.pietro.hexagonal.core.ports;

import com.pietro.hexagonal.core.domain.PessoaDomain;

import java.util.List;

public interface PessoaServicePort {

    PessoaDomain savePessoa(PessoaDomain pessoaDomain);

    List<PessoaDomain> findAll();

}
