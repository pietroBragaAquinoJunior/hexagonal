package com.pietro.hexagonal.adapters.dtos.entrada;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// O normal é ter dois dtos para cada entidade. Um dto de Entrada e outro de Saída. Entrada com validação.
// Não usar @Data pois tem coisas a mais que o necessário.

@Getter
@Setter
@NoArgsConstructor
public class LivroRequestDto {
    @NotBlank(message = "O título do livro é obrigatório.")
    private String titulo;
}
