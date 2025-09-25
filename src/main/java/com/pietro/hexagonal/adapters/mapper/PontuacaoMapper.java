package com.pietro.hexagonal.adapters.mapper;

import org.mapstruct.Mapper;

import com.pietro.hexagonal.adapters.dtos.saida.PontuacaoResponseDto;
import com.pietro.hexagonal.core.domain.PontuacaoDomain;

@Mapper(componentModel = "spring")
public interface PontuacaoMapper {
    // Response - Domain
    PontuacaoResponseDto toPontuacaoResponseDto(PontuacaoDomain pontuacaoDomain);
    PontuacaoDomain toPontuacaoDomain(PontuacaoResponseDto pontuacaoResponseDto);
}
