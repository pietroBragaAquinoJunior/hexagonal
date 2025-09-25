package com.pietro.hexagonal.adapters.inbound.controllers;

import com.pietro.hexagonal.adapters.dtos.entrada.PessoaRequestDto;
import com.pietro.hexagonal.adapters.dtos.saida.PessoaResponseDto;
import com.pietro.hexagonal.adapters.mapper.PessoaMapper;
import com.pietro.hexagonal.core.domain.PessoaDomain;
import com.pietro.hexagonal.core.ports.PessoaServicePort;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaServicePort pessoaServicePort;
    private final PessoaMapper pessoaMapper;

    public PessoaController(PessoaServicePort pessoaServicePort, PessoaMapper pessoaMapper) {
        this.pessoaServicePort = pessoaServicePort;
        this.pessoaMapper = pessoaMapper;
    }

    @GetMapping
    public ResponseEntity<List<PessoaResponseDto>> listarPessoas() {
        // if(true){
        //     throw new MinhaExcecaoCustom("Essa é minha exceção customizada!, O handler colocará o Status adequado. Não se afobe...");
        // }
        List<PessoaDomain> pessoaDomainList = pessoaServicePort.findAll();
        List<PessoaResponseDto> pessoaResponseDtos = pessoaDomainList.stream()
                                                    .map(pessoaDomain -> pessoaMapper.toPessoaResponseDto(pessoaDomain))
                                                    .toList();
        return ResponseEntity.ok(pessoaResponseDtos);
    }

    @PostMapping
    public ResponseEntity<PessoaResponseDto> salvarPessoa(@Valid @RequestBody PessoaRequestDto pessoaRequestDto) {
        PessoaDomain pessoaDomain = pessoaMapper.toPessoaDomain(pessoaRequestDto);
        PessoaDomain pessoaDomainSaved = pessoaServicePort.savePessoa(pessoaDomain);
        PessoaResponseDto pessoaResponseDto = pessoaMapper.toPessoaResponseDto(pessoaDomainSaved);
        return ResponseEntity.ok(pessoaResponseDto);
    }

}
