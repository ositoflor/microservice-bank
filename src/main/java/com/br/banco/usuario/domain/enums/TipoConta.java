package com.br.banco.usuario.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TipoConta {
    PF("PF", "Pessoa Fisica"),
    PJ("PJ", "Pessoa Juridica"),
    GOV("GOV", "Governamental");

    private String sigla;

    private String descricao;
}
