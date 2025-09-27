package com.pietro.hexagonal.adapters.outbound.persistence.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "pessoa")
// 1. ToString: Exclua todos os relacionamentos.
@ToString(exclude = {"livros", "viagens"})
// 2. Equals/HashCode: Use apenas o campo @Id, que é estável e único.
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PessoaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private UUID id;
    @Column(nullable = false, length = 150)
    private String nome;
    @Column(nullable = false, length = 150)
    private String email;
    @Column(nullable = false, length = 30)
    private String cpf;

    // Por padrão o JPA é Lazy em One to Many. Ele só buscará se em um método Transactional você tentar acessar o recurso.

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LivroEntity> livros;


    @ManyToMany(fetch = FetchType.LAZY) // M-a-M é melhor com LAZY por padrão
    @JoinTable(
        name = "pessoa_viagem", // Nome da tabela de ligação (exatamente como no Flyway)
        joinColumns = @JoinColumn(name = "pessoa_id"), // Coluna que referencia Pessoa
        inverseJoinColumns = @JoinColumn(name = "viagem_id") // Coluna que referencia Viagem
    )
    private Set<ViagemEntity> viagens = new HashSet<>();

}
