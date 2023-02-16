package com.br.banco.usuario.services.impl;

import com.br.banco.usuario.domain.Cliente;
import com.br.banco.usuario.repositories.ClienteRepository;
import com.br.banco.usuario.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    ClienteRepository usuarioRepository;

    @Override
    public Cliente save(Cliente usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Cliente> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Cliente> findById(String id) {
        return usuarioRepository.findById(id);
    }
}
