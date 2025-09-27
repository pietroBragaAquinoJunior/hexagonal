package com.pietro.hexagonal.adapters.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pietro.hexagonal.adapters.dtos.entrada.PessoaRequestDto;
import com.pietro.hexagonal.adapters.dtos.saida.PessoaResponseDto;
import com.pietro.hexagonal.adapters.outbound.persistence.entities.PessoaEntity;
import com.pietro.hexagonal.core.domain.PessoaDomain;

@Mapper(componentModel = "spring")
public interface PessoaMapper {
    // Response - Domain
    PessoaResponseDto toPessoaResponseDto(PessoaDomain pessoaDomain);
    @Mapping(target = "id", ignore = true )
    @Mapping(target = "cpf", ignore = true )
    @Mapping(target = "livros", ignore = true )
     @Mapping(target = "viagens", ignore = true )
    PessoaDomain toPessoaDomain(PessoaResponseDto pessoaResponseDto);
    // Request - Domain
    PessoaRequestDto toPessoaRequestDto(PessoaDomain pessoaDomain);
    @Mapping(target = "id", ignore = true )
    @Mapping(target = "livros", ignore = true )
    @Mapping(target = "viagens", ignore = true )
    PessoaDomain toPessoaDomain(PessoaRequestDto pessoaRequestDto);
    // Entity - Domain
    @Mapping(target = "livros", ignore = true )
    PessoaEntity toPessoaEntity(PessoaDomain pessoaDomain);
    PessoaDomain toPessoaDomain(PessoaEntity pessoaEntity);
}
