package com.pietro.hexagonal.adapters.outbound.persistence;

import com.pietro.hexagonal.adapters.outbound.persistence.entities.PessoaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PessoaJpaRepository extends JpaRepository<PessoaEntity, UUID> {

    Page<PessoaEntity> findAll(Pageable pageable);

}
