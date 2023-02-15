package com.br.banco.usuario.domain.enums;

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

    TipoTransacao(String sigla, String descricao) {
        this.sigla = sigla;
        this.descricao = descricao;
    }

    public String getSigla() {
        return sigla;
    }

    public String getDescricao() {
        return descricao;
    }
}
