package com.pietro.hexagonal.adapters.outbound.persistence;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pietro.hexagonal.adapters.outbound.persistence.entities.LivroEntity;

public interface LivroJpaRepository extends JpaRepository<LivroEntity, UUID> {

}
