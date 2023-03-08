package com.br.banco.usuario.services;

import com.br.banco.usuario.domain.Solicitacao;

public interface SolicitacaoService {
    Solicitacao save(Solicitacao solicitacao);
    Solicitacao findById(String id);
}
