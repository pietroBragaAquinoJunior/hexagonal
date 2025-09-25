package com.pietro.hexagonal.adapters.dtos.saida;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Se você precisar de um DTO que traga mais informações (principalmente de outras entidades) você cria mais com um nome tipo esse: PessoaDetailResponseDto
// PessoaSummaryResponseDTO, PessoaUpdateRequestDTO (Patch)

@Getter
@Setter
@NoArgsConstructor
public class PessoaResponseDto {
    private String nome;
    private String email;   
}
