package com.br.banco.usuario.services;

import com.br.banco.usuario.domain.Conta;
import com.br.banco.usuario.domain.Cliente;
import com.br.banco.usuario.dtos.ContaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ContaService {
    Conta save(ContaDto contaDto);
    Page<Conta> findAll(Pageable pageable);
    Optional<Conta> findById(String id);
}
