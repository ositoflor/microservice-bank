package com.br.banco.usuario.services.impl;

import com.br.banco.usuario.domain.Cliente;
import com.br.banco.usuario.domain.Conta;
import com.br.banco.usuario.repositories.UsuarioRepository;
import com.br.banco.usuario.services.ContaService;
import com.br.banco.usuario.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements ClienteService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Cliente save(Cliente usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Page<Cliente> findAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    @Override
    public Optional<Cliente> findById(String id) {
        return usuarioRepository.findById(id);
    }
}
