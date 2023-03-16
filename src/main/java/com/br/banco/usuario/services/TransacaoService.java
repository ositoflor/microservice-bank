package com.br.banco.usuario.services;

import com.br.banco.usuario.domain.Transacao;
import com.br.banco.usuario.domain.enums.TipoTransacao;
import com.br.banco.usuario.dtos.DefaultExtratoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransacaoService {
    Transacao save(Transacao transacao);
    Page<Transacao> findAll(Pageable pageable);
    Transacao findById(String id);
    Page<Transacao> findByIdConta(String id,Pageable pageable);
    Page<Transacao> findByIdContaByTipoTransacao(String id, TipoTransacao tipoTransacao,Pageable pageable);
}
