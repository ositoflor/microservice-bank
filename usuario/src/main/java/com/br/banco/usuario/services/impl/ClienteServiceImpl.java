package com.br.banco.usuario.services.impl;

import com.br.banco.usuario.domain.Cliente;
import com.br.banco.usuario.exceptionHandler.DefaultNotFound;
import com.br.banco.usuario.repositories.ClienteRepository;
import com.br.banco.usuario.services.ClienteService;
import com.br.banco.usuario.services.utils.ValidadorDocumento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    ClienteRepository usuarioRepository;

    @Override
    public Cliente save(Cliente cliente) {
        String documento = ValidadorDocumento.validar(cliente);
        cliente.setDocumento(documento);
        return usuarioRepository.save(cliente);
    }

    @Override
    public Page<Cliente> findAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    @Override
    public Cliente findById(String id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new DefaultNotFound("Cliente não encontrado."));
    }
}
