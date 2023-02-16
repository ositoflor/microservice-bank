package com.br.banco.usuario.controllers;

import com.br.banco.usuario.domain.Conta;
import com.br.banco.usuario.dtos.ContaDto;
import com.br.banco.usuario.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "/conta")
public class ContaController {

    @Autowired
    ContaService contaService;

    @PostMapping(value = "/add")
    public ResponseEntity<Conta> save(@RequestBody ContaDto contaDto) {
        System.out.println(contaDto);
        Conta conta1 = contaService.save(contaDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(conta1.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page> findAll(Pageable pageable) {
        Page<Conta> content = contaService.findAll(pageable);
        return ResponseEntity.ok().body(content);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Conta>> findById(@PathVariable(value = "id") String id){
        Optional<Conta> conta = contaService.findById(id);
        return ResponseEntity.ok().body(conta);
    }
}
