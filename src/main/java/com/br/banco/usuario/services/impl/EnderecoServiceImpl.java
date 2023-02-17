package com.br.banco.usuario.services.impl;

import com.br.banco.usuario.domain.Endereco;
import com.br.banco.usuario.exceptionHandler.EnderecoExceptions.EnderecoNotFound;
import com.br.banco.usuario.repositories.EnderecoRepository;
import com.br.banco.usuario.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;
    @Override
    public Endereco save(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    @Override
    public Page<Endereco> findAll(Pageable pageable) {
        return enderecoRepository.findAll(pageable);
    }

    @Override
    public Endereco findById(String id) {
        return enderecoRepository.findById(id).orElseThrow(()-> new EnderecoNotFound("Endereço não encontrado."));
    }
}
