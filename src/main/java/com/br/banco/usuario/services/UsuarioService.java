package com.br.banco.usuario.services;

import com.br.banco.usuario.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioService {
    Usuario save(Usuario usuario);
    Page<Usuario> findAll(Pageable pageable);
    Optional<Usuario> findById(String id);
}
