package com.pietro.hexagonal.core.domain;

import java.util.UUID;

/**
 * Sem tecnologia, está no core.
 * Não é exatamente igual a entidade do banco. Os relacionamentos só devem aparecer aqui se houver regras de negócio
 * que precisem desses objetos. Caso contrário, não defina-os aqui para que não sejam mapeados e o ganho de perfomance seja máximizado.
 *
 **/

public class LivroDomain {
    private UUID id;
    private String titulo;
    private PessoaDomain pessoaDomain;
    public LivroDomain() {
    }
    public LivroDomain(UUID id, String titulo, PessoaDomain pessoaDomain) {
        this.id = id;
        this.titulo = titulo;
        this.pessoaDomain = pessoaDomain;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public PessoaDomain getPessoaDomain() {
        return pessoaDomain;
    }
    public void setPessoaDomain(PessoaDomain pessoaDomain) {
        this.pessoaDomain = pessoaDomain;
    }

}
