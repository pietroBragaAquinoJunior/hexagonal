package com.pietro.hexagonal.core.services;

import com.pietro.hexagonal.core.domain.PageInfo;
import com.pietro.hexagonal.core.domain.PessoaDomain;
import com.pietro.hexagonal.core.ports.PessoaPersistencePort;
import com.pietro.hexagonal.core.ports.PessoaServicePort;

import java.util.List;

/**
 *
 * Essa classe precisaria do @Service do Lombok, mas como está dentro do CORE da aplicação precisamos criar o Bean manualmente.
 * Faremos essa configuração em um Adapter de Configuração Específico.
 * Essa classe também utilizava o repository do jpa diretamente, porém como ele está dentro do core, ele só pode se comunicar com portas
 * Onde tiver Page, como estamos no core só podemos usar PageInfo (Lembrando)
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
    public List<PessoaDomain> findAll(PageInfo pageable) {
        return pessoaPersistencePort.findAll(pageable);
    }

}
