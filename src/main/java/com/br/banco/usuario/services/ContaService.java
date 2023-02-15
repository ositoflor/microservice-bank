package com.br.banco.usuario.services;

import com.br.banco.usuario.domain.Conta;
import com.br.banco.usuario.domain.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ContaService {

    Conta criarConta(Cliente usuario);
    Conta save(Conta usuario);
    Page<Conta> findAll(Pageable pageable);
    Optional<Conta> findById(String id);
}