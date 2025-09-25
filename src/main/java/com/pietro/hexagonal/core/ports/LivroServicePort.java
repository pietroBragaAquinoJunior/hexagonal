package com.pietro.hexagonal.core.ports;

import java.util.List;
import java.util.UUID;

import com.pietro.hexagonal.core.domain.LivroDomain;

public interface LivroServicePort {

    LivroDomain saveLivro(UUID pessoaId, LivroDomain livroDomain);

    List<LivroDomain> findAll();

}
