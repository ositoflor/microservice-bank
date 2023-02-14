package com.br.banco.usuario.controllers;

import com.br.banco.usuario.domain.Usuario;
import com.br.banco.usuario.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody Usuario novoUsuario) {
        Usuario user = usuarioService.save(novoUsuario);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page> findAll(Pageable pageable) {
        Page<Usuario> content = usuarioService.findAll(pageable);
        return ResponseEntity.ok().body(content);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Usuario>> findById(@PathVariable(value = "id") String id){
        Optional<Usuario> usuario = usuarioService.findById(id);
        return ResponseEntity.ok().body(usuario);
    }
}
