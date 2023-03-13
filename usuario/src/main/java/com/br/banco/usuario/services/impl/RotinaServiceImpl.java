package com.br.banco.usuario.services.impl;

import com.br.banco.usuario.domain.enums.TipoConta;
import com.br.banco.usuario.repositories.ContaRepository;
import com.br.banco.usuario.services.RotinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class RotinaServiceImpl implements RotinaService {
    @Autowired
    ContaRepository contaRepository;

    private static final String TIME_ZONE = "America/Sao_Paulo";
    @Override
    @Scheduled(cron = "0/20 * * * * *", zone = TIME_ZONE)
    public void rotina() {
            for (TipoConta tipoConta : TipoConta.values()) {
                contaRepository.updateSaques(tipoConta,tipoConta.getQuantidadeSaque());
            }
            System.out.println("Rodou a rotina :D");
    }
}
