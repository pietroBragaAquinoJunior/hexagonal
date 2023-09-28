package com.pietro.hexagonal.adapters.inbound.controllers;

import com.pietro.hexagonal.adapters.dtos.entrada.PessoaDto;
import com.pietro.hexagonal.core.domain.PageInfo;
import com.pietro.hexagonal.core.domain.PessoaDomain;
import com.pietro.hexagonal.core.ports.PessoaServicePort;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class PessoaController {

    private final PessoaServicePort pessoaServicePort;

    public PessoaController(PessoaServicePort pessoaServicePort) {
        this.pessoaServicePort = pessoaServicePort;
    }

    @PostMapping("/pessoas")
    public ResponseEntity<PessoaDomain> salvarPessoa(@RequestBody PessoaDto pessoaDto) {
        PessoaDomain pessoaDomain = new PessoaDomain();
        BeanUtils.copyProperties(pessoaDto, pessoaDomain);
        return ResponseEntity.ok(pessoaServicePort.savePessoa(pessoaDomain));
    }

    @GetMapping("/pessoas")
    public ResponseEntity<List<PessoaDomain>> listarPessoas(Pageable pageable) {
        PageInfo pageInfo = new PageInfo();
        BeanUtils.copyProperties(pageable, pageInfo);
        List<PessoaDomain> pessoaDomainList = pessoaServicePort.findAll(pageInfo);
        return ResponseEntity.ok(pessoaDomainList);
    }

}
