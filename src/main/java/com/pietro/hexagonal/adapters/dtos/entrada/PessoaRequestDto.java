package com.pietro.hexagonal.adapters.dtos.entrada;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// O normal é ter dois dtos para cada entidade. Um dto de Entrada e outro de Saída. Entrada com validação.
// Não usar @Data pois tem coisas a mais que o necessário.

@Getter
@Setter
@NoArgsConstructor
public class PessoaRequestDto {
    @NotBlank(message = "O Nome da Pessoa é obrigatório.")
    private String nome;
    @NotBlank(message = "O Email da Pessoa é obrigatório.")
    @Email(message = "Email inválido")
    private String email;
    @NotBlank(message = "O Cpf da Pessoa é obrigatório.")
    @Size(min = 11, max = 11, message = "O Cpf da Pessoa deve ter 11 dígitos.")
    private String cpf;
}
