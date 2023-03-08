package com.br.banco.usuario.services.impl;

import com.br.banco.usuario.domain.Solicitacao;
import com.br.banco.usuario.exceptionHandler.DefaultNotFound;
import com.br.banco.usuario.repositories.SolicitacaoRepository;
import com.br.banco.usuario.services.SolicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolicitacaoServiceImpl implements SolicitacaoService {

    @Autowired
    SolicitacaoRepository solicitacaoRepository;
    @Override
    public Solicitacao save(Solicitacao solicitacao) {
        Solicitacao solicitacao1 = solicitacaoRepository.save(solicitacao);
        return solicitacao1;
    }

    @Override
    public Solicitacao findById(String id) {
        var solicitacao = solicitacaoRepository.findById(id);
        return solicitacao.orElseThrow(() -> new DefaultNotFound("Solicitação não encontrada."));
    }
}
