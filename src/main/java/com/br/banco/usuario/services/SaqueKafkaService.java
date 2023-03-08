package com.br.banco.usuario.services;

import com.br.banco.usuario.dtos.RespostaSolicitacaoDto;

public interface SaqueKafkaService {
    void validarSaque(RespostaSolicitacaoDto respostaSolicitacaoDto);
}
