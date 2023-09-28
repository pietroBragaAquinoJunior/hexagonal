package com.pietro.hexagonal.adapters.outbound.persistence.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name = "pessoa")
public class PessoaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false, length = 150)
    private String nome;
    @Column(nullable = false, length = 150)
    private String email;
    @Column(nullable = false, length = 30)
    private String cpf;

}
