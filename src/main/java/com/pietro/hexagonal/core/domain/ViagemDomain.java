package com.pietro.hexagonal.core.domain;

import java.util.Set;
import java.util.UUID;

public class ViagemDomain {
    private UUID id;
    private String destino;
    private Set<PessoaDomain> pessoas;
    
    public ViagemDomain() {
    }

    public ViagemDomain(UUID id, String destino, Set<PessoaDomain> pessoas) {
        this.id = id;
        this.destino = destino;
        this.pessoas = pessoas;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Set<PessoaDomain> getPessoas() {
        return pessoas;
    }

    public void setPessoas(Set<PessoaDomain> pessoas) {
        this.pessoas = pessoas;
    }
    
   
    
}
