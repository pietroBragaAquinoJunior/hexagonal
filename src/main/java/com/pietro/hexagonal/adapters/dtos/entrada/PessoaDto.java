package com.pietro.hexagonal.adapters.dtos.entrada;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PessoaDto {
    @NotBlank(message = "O Nome da Pessoa é obrigatório.")
    private String nome;
    @NotBlank(message = "O Email da Pessoa é obrigatório.")
    @Email(message = "Email inválido")
    private String email;
    @NotBlank(message = "O Cpf da Pessoa é obrigatório.")
    @Size(min = 11, max = 11, message = "O Cpf da Pessoa deve ter 11 dígitos.")
    private String cpf;
    public PessoaDto() {
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
