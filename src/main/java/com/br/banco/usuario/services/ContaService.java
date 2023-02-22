package com.br.banco.usuario.services;

import com.br.banco.usuario.domain.Conta;
import com.br.banco.usuario.dtos.ContaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ContaService {
    Conta save(ContaDto contaDto);
    Page<Conta> findAll(Pageable pageable);
    Conta findById(String id);
    Conta findByIdCliente(String id);
}
