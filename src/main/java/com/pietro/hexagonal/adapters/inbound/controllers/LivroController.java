package com.pietro.hexagonal.adapters.inbound.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pietro.hexagonal.adapters.dtos.entrada.LivroRequestDto;
import com.pietro.hexagonal.adapters.dtos.saida.LivroResponseDto;
import com.pietro.hexagonal.adapters.mapper.LivroMapper;
import com.pietro.hexagonal.core.domain.LivroDomain;
import com.pietro.hexagonal.core.ports.LivroServicePort;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/livros")
public class LivroController {
    
    private final LivroServicePort livroServicePort;
    private final LivroMapper livroMapper;

    public LivroController(LivroServicePort livroServicePort, LivroMapper livroMapper) {
        this.livroServicePort = livroServicePort;
        this.livroMapper = livroMapper;
    }

    @GetMapping
    public ResponseEntity<List<LivroResponseDto>> listarLivros() {
        // if(true){
        //     throw new MinhaExcecaoCustom("Essa é minha exceção customizada!, O handler colocará o Status adequado. Não se afobe...");
        // }
        List<LivroDomain> livroDomainList = livroServicePort.findAll();
        List<LivroResponseDto> livroResponseDtos = livroDomainList.stream()
                                                    .map(livroDomain -> livroMapper.toLivroResponseDto(livroDomain))
                                                    .toList();
        return ResponseEntity.ok(livroResponseDtos);
    }

    @PostMapping("/{pessoaId}")
    public ResponseEntity<LivroResponseDto> salvarLivro(@PathVariable UUID pessoaId, @Valid @RequestBody LivroRequestDto livroRequestDto) {
        LivroDomain livroDomain = livroMapper.toLivroDomain(livroRequestDto);
        LivroDomain livroDomainSaved = livroServicePort.saveLivro(pessoaId, livroDomain);
        LivroResponseDto livroResponseDto = livroMapper.toLivroResponseDto(livroDomainSaved);
        return ResponseEntity.ok(livroResponseDto);
    }

}
