package com.pietro.hexagonal.adapters.inbound.controllers;

import com.pietro.hexagonal.adapters.dtos.entrada.PessoaRequestDto;
import com.pietro.hexagonal.adapters.dtos.saida.PessoaResponseDto;
import com.pietro.hexagonal.adapters.dtos.saida.PontuacaoResponseDto;
import com.pietro.hexagonal.adapters.mapper.PessoaMapper;
import com.pietro.hexagonal.adapters.mapper.PontuacaoMapper;
import com.pietro.hexagonal.core.domain.PessoaDomain;
import com.pietro.hexagonal.core.domain.PontuacaoDomain;
import com.pietro.hexagonal.core.ports.PessoaServicePort;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaServicePort pessoaServicePort;
    private final PessoaMapper pessoaMapper;
    private final PontuacaoMapper pontuacaoMapper;

    public PessoaController(PessoaServicePort pessoaServicePort, PessoaMapper pessoaMapper, PontuacaoMapper pontuacaoMapper) {
        this.pessoaServicePort = pessoaServicePort;
        this.pessoaMapper = pessoaMapper;
        this.pontuacaoMapper = pontuacaoMapper;
    }

    @GetMapping
    public ResponseEntity<List<PessoaResponseDto>> listarPessoas() {
        List<PessoaDomain> pessoaDomainList = pessoaServicePort.findAll();
        List<PessoaResponseDto> pessoaResponseDtos = pessoaDomainList.stream().map(pessoaDomain -> pessoaMapper.toPessoaResponseDto(pessoaDomain)).toList();
        return ResponseEntity.ok(pessoaResponseDtos);
    }

    @PostMapping
    public ResponseEntity<PessoaResponseDto> salvarPessoa(@Valid @RequestBody PessoaRequestDto pessoaRequestDto) {
        PessoaDomain pessoaDomain = pessoaMapper.toPessoaDomain(pessoaRequestDto);
        PessoaDomain pessoaDomainSaved = pessoaServicePort.savePessoa(pessoaDomain);
        PessoaResponseDto pessoaResponseDto = pessoaMapper.toPessoaResponseDto(pessoaDomainSaved);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaResponseDto);
    }


    // MÉTODO MUITO IMPORTANTE E VAI ILUSTRAR A MANEIRA CORRETA DE SE FAZER UM CÁLCULO
    // IMAGINE QUE EU TENHA UMA PESSOA QUE TEM VÁRIAS NOTAS. Se minha lógica de negócio for fazer findById encontrar e depois acessar,
    // O jpa vai fazer diversas chamadas ao banco de dados. Nós não queremos ter esse tipo de lógica pois isso pode gerar um problema sério
    // de perfomance conhecido como n+1. Então nós já devemos fazer uma única chamada ao repositório (com um nome grande provavelmente) que me traga
    // TUDO QUE EU PRECISO já unido para alimentar a entidade e posteriormente alimentar o Domain. Com o domain alimentado eu posso fazer o cálculo, 
    // sem me preocupar com desempenho terrível.

    @GetMapping("/{pessoaId}/pontuacao")
    public ResponseEntity<PontuacaoResponseDto> calcularPontuacao(@PathVariable UUID pessoaId) {
        PontuacaoDomain pontuacaoDomain = pessoaServicePort.calcularPontuacao(pessoaId);
        PontuacaoResponseDto pontuacaoResponseDto = pontuacaoMapper.toPontuacaoResponseDto(pontuacaoDomain);
        return ResponseEntity.ok(pontuacaoResponseDto);
    }

    // PODERIA SER FEITO DO JEITO ERRADO, LEIA O README (JEITO ERRADO COM PROJEÇÃO / INTERFACE E RETORNAR ISSO DIRETAMENTE.)
    


    // OUTRO MÉTODO IMPORTANTE, COMO FAZER DA MANEIRA CORRETA (EVITANDO N+1, CARREGANDO O DOMAIN COM VIAGENS E DEPOIS EXECUTANDO O CÁLCULO) ~ MANY TO MANY.
    @GetMapping("/{pessoaId}/pontuacao-viagem")
    public ResponseEntity<PontuacaoResponseDto> calcularPontuacaoViagem(@PathVariable UUID pessoaId) {
        PontuacaoDomain pontuacaoDomain = pessoaServicePort.calcularPontuacaoViagem(pessoaId);
        PontuacaoResponseDto pontuacaoResponseDto = pontuacaoMapper.toPontuacaoResponseDto(pontuacaoDomain);
        return ResponseEntity.ok(pontuacaoResponseDto);
    }
    

}
