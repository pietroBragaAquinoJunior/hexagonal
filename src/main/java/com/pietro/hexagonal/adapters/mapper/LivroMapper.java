package com.pietro.hexagonal.adapters.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pietro.hexagonal.adapters.dtos.entrada.LivroRequestDto;
import com.pietro.hexagonal.adapters.dtos.saida.LivroResponseDto;
import com.pietro.hexagonal.adapters.outbound.persistence.entities.LivroEntity;
import com.pietro.hexagonal.core.domain.LivroDomain;

@Mapper(componentModel = "spring")
public interface LivroMapper {
    // Response - Domain
    LivroResponseDto toLivroResponseDto(LivroDomain livroDomain);
    @Mapping(target = "id", ignore = true )
    LivroDomain toLivroDomain(LivroResponseDto livroResponseDto);
    // Request - Domain
    LivroRequestDto toLivroRequestDto(LivroDomain livroDomain);
    @Mapping(target = "id", ignore = true )
    LivroDomain toLivroDomain(LivroRequestDto livroRequestDto);
    // Entity - Domain
    @Mapping(target = "pessoa", ignore = true )
    LivroEntity toLivroEntity(LivroDomain livroDomain);
    LivroDomain toLivroDomain(LivroEntity livroEntity);
}
