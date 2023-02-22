package com.br.banco.usuario.services;

import com.br.banco.usuario.domain.Transacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransacaoService {
    Transacao save(Transacao transacao);
    Page<Transacao> findAll(Pageable pageable);
    Transacao findById(String id);
    Page<Transacao> findByIdConta(String id, Pageable pageable);
}
