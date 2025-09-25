package com.pietro.hexagonal.adapters.outbound.persistence;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.pietro.hexagonal.adapters.exceptions.NotFoundCustomException;
import com.pietro.hexagonal.adapters.mapper.LivroMapper;
import com.pietro.hexagonal.adapters.outbound.persistence.entities.LivroEntity;
import com.pietro.hexagonal.adapters.outbound.persistence.entities.PessoaEntity;
import com.pietro.hexagonal.core.domain.LivroDomain;
import com.pietro.hexagonal.core.ports.LivroPersistencePort;

import jakarta.transaction.Transactional;

@Service
public class LivroPersistencePortImpl implements LivroPersistencePort {

    private final LivroJpaRepository livroJpaRepository;
    private final LivroMapper livroMapper;
    private final PessoaJpaRepository pessoaJpaRepository;

    public LivroPersistencePortImpl(LivroJpaRepository livroJpaRepository, LivroMapper livroMapper, PessoaJpaRepository pessoaJpaRepository) {
        this.livroJpaRepository = livroJpaRepository;
        this.livroMapper = livroMapper;
        this.pessoaJpaRepository = pessoaJpaRepository;
    }

    // Métodos de escrita precisam do @Transactional.

    @Override
    @Transactional
    public LivroDomain saveLivro(UUID pessoaId, LivroDomain livroDomain) {
        PessoaEntity pessoaEntity = pessoaJpaRepository.findById(pessoaId)
                                        .orElseThrow(() -> new NotFoundCustomException("Não foi possível encontrar a Pessoa."));
        LivroEntity livroEntity = livroMapper.toLivroEntity(livroDomain);
        livroEntity.setPessoa(pessoaEntity);
        LivroEntity livroEntitySaved = livroJpaRepository.save(livroEntity);
        LivroDomain livroDomainSaved = livroMapper.toLivroDomain(livroEntitySaved);
        return livroDomainSaved;
    }

    @Override
    public List<LivroDomain> findAll() {
        List<LivroEntity> livroEntityList = livroJpaRepository.findAll();
        List<LivroDomain> livroDomainList = livroEntityList.stream()
                                                .map(livroEntity -> livroMapper.toLivroDomain(livroEntity))
                                                .toList();
        return livroDomainList;
    }
}

