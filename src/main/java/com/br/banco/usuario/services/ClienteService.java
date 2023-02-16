package com.br.banco.usuario.services;

import com.br.banco.usuario.domain.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    Cliente save(Cliente usuario);
    List<Cliente> findAll();
    Optional<Cliente> findById(String id);
}
