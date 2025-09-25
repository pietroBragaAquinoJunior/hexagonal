package com.pietro.hexagonal.adapters.outbound.persistence;

import com.pietro.hexagonal.adapters.outbound.persistence.entities.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface PessoaJpaRepository extends JpaRepository<PessoaEntity, UUID> {

    @Query("select p from PessoaEntity p join fetch p.livros where p.id = :pessoaId")
    Optional<PessoaEntity> findByIdWithLivros(UUID pessoaId);

}
