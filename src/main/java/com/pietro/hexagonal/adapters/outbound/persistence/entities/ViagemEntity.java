package com.pietro.hexagonal.adapters.outbound.persistence.entities;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "viagem")
// 1. ToString: Exclua todos os relacionamentos.
@ToString(exclude = {"pessoas"})
// 2. Equals/HashCode: Use apenas o campo @Id, que é estável e único.
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ViagemEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, length = 150)
    private String destino;
    
    @ManyToMany(mappedBy = "viagens")
    private Set<PessoaEntity> pessoas;

}