package com.br.banco.usuario.services.impl;

import com.br.banco.usuario.domain.Solicitacao;
import com.br.banco.usuario.dtos.RespostaSolicitacaoDto;
import com.br.banco.usuario.services.SaqueKafkaService;
import com.br.banco.usuario.services.SolicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SaquekafkaServiceImpl implements SaqueKafkaService {

    @Autowired
    SolicitacaoService solicitacaoService;
    @Override
    @KafkaListener(topics = "resposta_solicitacao", containerFactory = "solicitacaoKafkaListenerFactory", groupId = "group_json")
    public void validarSaque(RespostaSolicitacaoDto respostaSolicitacaoDto) {
        Solicitacao solicitacao = solicitacaoService.findById(respostaSolicitacaoDto.getIdSolicitacao());
        solicitacao.setStatusSolicitacao(respostaSolicitacaoDto.getStatusSolicitacao());
        solicitacao.setValorDebitado(respostaSolicitacaoDto.getValorDebitado());
        solicitacaoService.save(solicitacao);
    }
}
