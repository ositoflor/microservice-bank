package com.br.banco.usuario.services;

import com.br.banco.usuario.domain.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ClienteService {
    Cliente save(Cliente usuario);
    Page<Cliente> findAll(Pageable pageable);
    Cliente findById(String id);
}
