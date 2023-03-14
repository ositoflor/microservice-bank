package com.br.banco.usuario.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TipoTransacao {
    BO("BO", "Boleto"),
    DE("DE", "Deposito"),
    DOC("DOC", "Documento de Ordem de Crédito"),
    PA("PA", "Pagamento"),
    PIX("PIX", "PIX"),
    SA("SA", "Saque"),
    TC("TC", "Transação de crédito"),
    TD("TD", "Transação de debito"),
    TED("TED", "Transferência Eletrônica Disponível");

    private String sigla;

    private String descricao;
}
