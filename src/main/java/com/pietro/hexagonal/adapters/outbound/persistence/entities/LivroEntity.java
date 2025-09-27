package com.pietro.hexagonal.adapters.outbound.persistence.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "livro")
// 1. ToString: Exclua todos os relacionamentos.
@ToString(exclude = {"pessoa"})
// 2. Equals/HashCode: Use apenas o campo @Id, que é estável e único.
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LivroEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, length = 150)
    private String titulo;

    // para Many to one e One to one o spring usa eager. Precisa ser trocado para Lazy.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id")
    private PessoaEntity pessoa;
}