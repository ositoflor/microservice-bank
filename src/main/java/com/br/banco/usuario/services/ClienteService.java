package com.br.banco.usuario.services;

import com.br.banco.usuario.domain.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ClienteService {
    Cliente save(Cliente usuario);
    Page<Cliente> findAll(Pageable pageable);
    Optional<Cliente> findById(String id);
}
