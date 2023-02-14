package com.br.banco.usuario.services.impl;

import com.br.banco.usuario.domain.Usuario;
import com.br.banco.usuario.infra.repositories.data.UsuarioRepository;
import com.br.banco.usuario.services.ContaService;
import com.br.banco.usuario.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ContaService contaService;
    @Override
    public Usuario save(Usuario usuario) {
        usuario.setConta(contaService.criarConta(usuario));
        return usuarioRepository.save(usuario);
    }

    @Override
    public Page<Usuario> findAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    @Override
    public Optional<Usuario> findById(String id) {
        return usuarioRepository.findById(id);
    }
}
