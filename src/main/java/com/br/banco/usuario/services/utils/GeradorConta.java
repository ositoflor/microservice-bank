package com.br.banco.usuario.services.utils;

import com.br.banco.usuario.domain.Conta;
import com.br.banco.usuario.domain.enums.TipoConta;
import com.br.banco.usuario.dtos.ContaDto;

public class GeradorConta {
    public static Conta gerarConta(ContaDto contaDto, TipoConta tipoConta) {
        Conta conta = new Conta();
        conta.setAgencia(contaDto.getAgencia());
        conta.setConta(Integer.parseInt(GeradorDigitos.gerarDigito(5)));
        conta.setDigito(Integer.parseInt(GeradorDigitos.gerarDigito(1)));
        conta.setIdCliente(contaDto.getIdCliente());
        conta.setTipoConta(tipoConta);
        return conta;
    }
}
