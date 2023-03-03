package com.br.banco.usuario.services.utils;

import com.br.banco.usuario.domain.Transacao;
import com.br.banco.usuario.domain.enums.TipoTransacao;

import java.time.LocalDateTime;
import java.util.Date;

public class GerarTransacao {

    public static Transacao gerar(String idConta, TipoTransacao tipoTransacao, Double valor) {
        LocalDateTime data = LocalDateTime.now();
        Transacao transacao = new Transacao();
        transacao.setIdConta(idConta);
        transacao.setTipoTransacao(tipoTransacao);
        transacao.setValor(valor);
        transacao.setDataHoraTransacao(data);
        return transacao;
    }
}
