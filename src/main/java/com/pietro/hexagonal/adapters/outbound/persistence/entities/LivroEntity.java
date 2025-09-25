package com.pietro.hexagonal.adapters.outbound.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "livro")
public class LivroEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false, length = 150)
    private String titulo;

    // para Many to one e One to one o spring usa eager. Precisa ser trocado para Lazy.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id")
    private PessoaEntity pessoa;
}