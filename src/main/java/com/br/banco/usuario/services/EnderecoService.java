package com.br.banco.usuario.services;

import com.br.banco.usuario.domain.Endereco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface EnderecoService {
    Endereco save(Endereco endereco);
    Page<Endereco> findAll(Pageable pageable);
    Endereco findById(String id);
}
