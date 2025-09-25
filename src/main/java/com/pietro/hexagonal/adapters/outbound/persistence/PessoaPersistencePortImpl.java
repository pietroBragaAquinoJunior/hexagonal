package com.pietro.hexagonal.adapters.outbound.persistence;

import com.pietro.hexagonal.adapters.outbound.persistence.entities.PessoaEntity;
import com.pietro.hexagonal.core.domain.PageInfo;
import com.pietro.hexagonal.core.domain.PessoaDomain;
import com.pietro.hexagonal.core.ports.PessoaPersistencePort;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaPersistencePortImpl implements PessoaPersistencePort {

    private final PessoaJpaRepository pessoaJpaRepository;
    private final ModelMapper modelMapper;

    public PessoaPersistencePortImpl(PessoaJpaRepository pessoaJpaRepository, ModelMapper modelMapper) {
        this.pessoaJpaRepository = pessoaJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PessoaDomain savePessoa(PessoaDomain pessoaDomain) {
        PessoaEntity pessoaEntitySalva = pessoaJpaRepository.save(modelMapper.map(pessoaDomain, PessoaEntity.class));
        return modelMapper.map(pessoaEntitySalva, PessoaDomain.class);
    }

    @Override
    public List<PessoaDomain> findAll(PageInfo pageInfo) {
        Pageable pageable = PageRequest.of(pageInfo.getPageNumber(), pageInfo.getPageSize());
        return pessoaJpaRepository.findAll(pageable).stream().map(pessoaEntity -> modelMapper.map(pessoaEntity, PessoaDomain.class)).toList();
    }
}
