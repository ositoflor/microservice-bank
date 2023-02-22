package com.br.banco.usuario.controllers;

import com.br.banco.usuario.domain.Transacao;
import com.br.banco.usuario.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
@RestController
@RequestMapping(value = "/transacao")
public class TransacaoController {

    @Autowired
    TransacaoService transacaoService;

    @PostMapping(value = "/add")
    public ResponseEntity<Transacao> save(@RequestBody Transacao novaTransacao) {
        Transacao user = transacaoService.save(novaTransacao);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<Transacao>> findAll(Pageable pageable) {
        Page<Transacao> content = transacaoService.findAll(pageable);
        return ResponseEntity.ok().body(content);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Transacao> findById(@PathVariable(value = "id") String id){
        Transacao usuario = transacaoService.findById(id);
        System.out.println(usuario);
        return ResponseEntity.ok().body(usuario);
    }

    @GetMapping(value = "/conta/{id}")
    public ResponseEntity<Page<Transacao>> findByIdConta(@PathVariable(value = "id") String id, Pageable pageable) {
        Page<Transacao> transacoes = transacaoService.findByIdConta(id,pageable);
        return ResponseEntity.ok().body(transacoes);
    }
}
