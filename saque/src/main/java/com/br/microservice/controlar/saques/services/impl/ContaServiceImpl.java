package com.br.microservice.controlar.saques.services.impl;

import com.br.microservice.controlar.saques.dtos.RespostaSolicitacaoDto;
import com.br.microservice.controlar.saques.dtos.SolicitacaoDto;
import com.br.microservice.controlar.saques.dtos.StatusSolicitacao;
import com.br.microservice.controlar.saques.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class ContaServiceImpl implements ContaService {

    @Autowired
    private KafkaTemplate<String, RespostaSolicitacaoDto> kafkaTemplate;

    private static final String TIME_ZONE = "America/Sao_Paulo";
    @Override
    @KafkaListener(topics = "Solicitacao-2", containerFactory = "solicitacaoKafkaListenerFactory", groupId = "group_json")
    public void validaSaque(@Payload SolicitacaoDto solicitacao) {
        RespostaSolicitacaoDto res = new RespostaSolicitacaoDto();
        res.setIdSolicitacao(solicitacao.getIdSolicitacao());
        if (solicitacao.getConta().getSaldo() < solicitacao.getValorSolicitado()) {
            res.setValorDebitado(0.0);
            res.setStatusSolicitacao(StatusSolicitacao.NA);
            kafkaTemplate.send("resposta_solicitacao", res);
            return;
        }
        if (solicitacao.getConta().getQuantidadeSaque() == 0) {
            var valorDebitado = solicitacao.getValorSolicitado() + solicitacao.getConta().getTipoConta().getValorSaque();
            if (solicitacao.getConta().getSaldo() < valorDebitado){
                res.setValorDebitado(0.0);
                res.setStatusSolicitacao(StatusSolicitacao.NA);
                kafkaTemplate.send("resposta_solicitacao", res);
                return;
            }
            res.setValorDebitado(valorDebitado);
            res.setStatusSolicitacao(StatusSolicitacao.AT);
            kafkaTemplate.send("resposta_solicitacao", res);

        }else {
            res.setValorDebitado(solicitacao.getValorSolicitado());
            res.setStatusSolicitacao(StatusSolicitacao.AT);
            kafkaTemplate.send("resposta_solicitacao", res);
        }

    }

    /*@Override
    @Scheduled(cron = "0/20 * * * * *", zone = TIME_ZONE)
    public void rotinaMensal() {
        for (TipoConta tipoConta : TipoConta.values()) {
            contaRepository.updateSaques(tipoConta,tipoConta.getQuantidadeSaque());
        }
        System.out.println("Rodou a rotina :D");
    }*/
}
