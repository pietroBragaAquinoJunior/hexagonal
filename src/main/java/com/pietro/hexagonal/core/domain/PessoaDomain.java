package com.pietro.hexagonal.core.domain;

import java.util.UUID;

/**
 *
 * Igual a classe Entidade do banco porém sem tecnologia (Livre de framework)
 *
 **/

public class PessoaDomain {

    private UUID id;
    private String nome;
    private String email;
    private String cpf;

    public PessoaDomain(UUID id, String nome, String email, String cpf) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }

    public PessoaDomain() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
