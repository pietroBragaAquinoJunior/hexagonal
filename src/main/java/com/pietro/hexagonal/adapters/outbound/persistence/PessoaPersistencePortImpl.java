package com.pietro.hexagonal.adapters.outbound.persistence;

import com.pietro.hexagonal.adapters.mapper.PessoaMapper;
import com.pietro.hexagonal.adapters.outbound.persistence.entities.PessoaEntity;
import com.pietro.hexagonal.core.domain.PessoaDomain;
import com.pietro.hexagonal.core.domain.exceptions.RecursoNaoEncontradoException;
import com.pietro.hexagonal.core.ports.PessoaPersistencePort;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PessoaPersistencePortImpl implements PessoaPersistencePort {

    private final PessoaJpaRepository pessoaJpaRepository;
    private final PessoaMapper pessoaMapper;

    public PessoaPersistencePortImpl(PessoaJpaRepository pessoaJpaRepository, PessoaMapper pessoaMapper) {
        this.pessoaJpaRepository = pessoaJpaRepository;
        this.pessoaMapper = pessoaMapper;
    }

    // Métodos de escrita precisam do @Transactional.

    @Override
    @Transactional
    public PessoaDomain savePessoa(PessoaDomain pessoaDomain) {
        PessoaEntity pessoaEntity = pessoaMapper.toPessoaEntity(pessoaDomain);
        PessoaEntity pessoaEntitySaved = pessoaJpaRepository.save(pessoaEntity);
        PessoaDomain pessoaDomainSaved = pessoaMapper.toPessoaDomain(pessoaEntitySaved);
        return pessoaDomainSaved;
    }

    @Override
    public List<PessoaDomain> findAll() {
        List<PessoaEntity> pessoaEntityList = pessoaJpaRepository.findAll();
        List<PessoaDomain> pessoaDomainList = pessoaEntityList.stream()
                                                .map(pessoaEntity -> pessoaMapper.toPessoaDomain(pessoaEntity))
                                                .toList();
        return pessoaDomainList;
    }

    @Override
    public PessoaDomain findByIdWithLivros(UUID pessoaId) {
        PessoaEntity pessoaEntity = pessoaJpaRepository.findByIdWithLivros(pessoaId)
                                    .orElseThrow(() -> new RecursoNaoEncontradoException("Não foi possível encontrar a Pessoa."));
        PessoaDomain pessoaDomain = pessoaMapper.toPessoaDomain(pessoaEntity);
        return pessoaDomain;
    }

    
}
