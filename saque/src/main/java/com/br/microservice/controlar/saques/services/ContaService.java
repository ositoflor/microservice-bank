package com.br.microservice.controlar.saques.services;

import com.br.microservice.controlar.saques.dtos.SolicitacaoDto;

public interface ContaService {

    void validaSaque(SolicitacaoDto solicitacao);
}
