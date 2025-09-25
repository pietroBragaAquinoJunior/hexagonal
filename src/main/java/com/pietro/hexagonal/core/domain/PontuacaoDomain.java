package com.pietro.hexagonal.core.domain;

/**
 * Sem tecnologia, está no core.
 * Não é exatamente igual a entidade do banco. Os relacionamentos só devem aparecer aqui se houver regras de negócio
 * que precisem desses objetos. Caso contrário, não defina-os aqui para que não sejam mapeados e o ganho de perfomance seja máximizado.
 *
 **/

public class PontuacaoDomain {
    private Integer pontuacao;
    public PontuacaoDomain() {
    }
    public PontuacaoDomain(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }
    public Integer getPontuacao() {
        return pontuacao;
    }
    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }
}
