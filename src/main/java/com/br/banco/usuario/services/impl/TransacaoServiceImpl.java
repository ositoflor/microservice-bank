package com.br.banco.usuario.services.impl;

import com.br.banco.usuario.domain.Transacao;
import com.br.banco.usuario.exceptionHandler.TransacaoExceptions.TransacaoNotFound;
import com.br.banco.usuario.repositories.TransacaoRepository;
import com.br.banco.usuario.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TransacaoServiceImpl implements TransacaoService {

    @Autowired
    TransacaoRepository transacaoRepository;
    @Override
    public Transacao save(Transacao transacao) {
        return transacaoRepository.save(transacao);
    }

    @Override
    public Page<Transacao> findAll(Pageable pageable) {
        return transacaoRepository.findAll(pageable);
    }

    @Override
    public Transacao findById(String id) {
        return transacaoRepository.findById(id).orElseThrow(()-> new TransacaoNotFound("Transação não encontrada."));
    }
}
