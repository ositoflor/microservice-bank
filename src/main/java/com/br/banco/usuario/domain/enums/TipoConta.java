package com.br.banco.usuario.domain.enums;

public enum TipoConta {
    PF("PF", "Pessoa Fisica"),
    PJ("PJ", "Pessoa Juridica"),
    GOV("GOV", "Governamental");

    private String sigla;

    private String descricao;

    TipoConta(String sigla, String descricao) {
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
