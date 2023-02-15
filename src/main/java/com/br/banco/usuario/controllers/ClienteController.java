package com.br.banco.usuario.controllers;

import com.br.banco.usuario.domain.Cliente;
import com.br.banco.usuario.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "/usuario")
public class ClienteController {

    @Autowired
    ClienteService usuarioService;

    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody Cliente novoUsuario) {
        System.out.println(novoUsuario);
        Cliente user = usuarioService.save(novoUsuario);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page> findAll(Pageable pageable) {
        Page<Cliente> content = usuarioService.findAll(pageable);
        return ResponseEntity.ok().body(content);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Cliente>> findById(@PathVariable(value = "id") String id){
        Optional<Cliente> usuario = usuarioService.findById(id);
        return ResponseEntity.ok().body(usuario);
    }
}
