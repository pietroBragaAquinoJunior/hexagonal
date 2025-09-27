package com.pietro.hexagonal.core.domain;

import java.util.Set;
import java.util.UUID;

/**
 * Sem tecnologia, está no core.
 * Não é exatamente igual a entidade do banco. Os relacionamentos só devem aparecer aqui se houver regras de negócio
 * que precisem desses objetos. Caso contrário, não defina-os aqui para que não sejam mapeados e o ganho de perfomance seja baixo.
 *
 **/

public class PessoaDomain {
    private UUID id;
    private String nome;
    private String email;
    private String cpf;
    private Set<LivroDomain> livros;
    private Set<ViagemDomain> viagens;
    public PessoaDomain() {
    }

    public PessoaDomain(UUID id, String nome, String email, String cpf, Set<LivroDomain> livros,
            Set<ViagemDomain> viagens) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.livros = livros;
        this.viagens = viagens;
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
    public Set<LivroDomain> getLivros() {
        return livros;
    }
    public void setLivros(Set<LivroDomain> livros) {
        this.livros = livros;
    }
    public Set<ViagemDomain> getViagens() {
        return viagens;
    }
    public void setViagens(Set<ViagemDomain> viagens) {
        this.viagens = viagens;
    }
   
   
}
