package com.pietro.hexagonal.core.ports;

import com.pietro.hexagonal.core.domain.PageInfo;
import com.pietro.hexagonal.core.domain.PessoaDomain;

import java.util.List;

public interface PessoaPersistencePort {

    PessoaDomain savePessoa(PessoaDomain pessoaDomain);

    List<PessoaDomain> findAll(PageInfo pageInfo);

}
