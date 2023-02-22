package com.br.banco.usuario.controllers;

import com.br.banco.usuario.domain.Conta;
import com.br.banco.usuario.domain.Transacao;
import com.br.banco.usuario.dtos.ContaDto;
import com.br.banco.usuario.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/conta")
public class ContaController {

    @Autowired
    ContaService contaService;

    @PostMapping(value = "/add")
    public ResponseEntity<Conta> save(@RequestBody @Valid ContaDto contaDto) {
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
    public ResponseEntity<Conta> findById(@PathVariable(value = "id") String id){
        Conta conta = contaService.findById(id);
        return ResponseEntity.ok().body(conta);
    }

    @GetMapping(value = "/cliente/{id}")
    public ResponseEntity<Conta> findByIdCliente(@PathVariable(value = "id") String id){
        Conta conta = contaService.findByIdCliente(id);
        return ResponseEntity.ok().body(conta);
    }
}
