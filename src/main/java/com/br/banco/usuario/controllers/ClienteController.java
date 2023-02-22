package com.br.banco.usuario.controllers;

import com.br.banco.usuario.domain.Cliente;
import com.br.banco.usuario.domain.Transacao;
import com.br.banco.usuario.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/usuario")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @PostMapping(value = "/add")
    public ResponseEntity<Cliente> save(@RequestBody Cliente novoUsuario) {
        Cliente user = clienteService.save(novoUsuario);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<Cliente>> findAll(Pageable pageable) {
        Page<Cliente> content = clienteService.findAll(pageable);
        return ResponseEntity.ok().body(content);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable(value = "id") String id){
        Cliente cliente = clienteService.findById(id);
        return ResponseEntity.ok().body(cliente);
    }



}
