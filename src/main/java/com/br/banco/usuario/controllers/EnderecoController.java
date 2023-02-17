package com.br.banco.usuario.controllers;

import com.br.banco.usuario.domain.Endereco;
import com.br.banco.usuario.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "/endereco")
public class EnderecoController {

    @Autowired
    EnderecoService enderecoService;

    @PostMapping(value = "/add")
    public ResponseEntity<Endereco> save(@RequestBody Endereco novoEndereco) {
        Endereco user = enderecoService.save(novoEndereco);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<Endereco>> findAll(Pageable pageable) {
        Page<Endereco> content = enderecoService.findAll(pageable);
        return ResponseEntity.ok().body(content);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Endereco> findById(@PathVariable(value = "id") String id){
        Endereco usuario = enderecoService.findById(id);
        System.out.println(usuario);
        return ResponseEntity.ok().body(usuario);
    }
}
