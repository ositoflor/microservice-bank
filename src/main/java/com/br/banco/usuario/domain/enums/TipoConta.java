package com.br.banco.usuario.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TipoConta {
    PF("PF", "Pessoa Fisica", 5),
    PJ("PJ", "Pessoa Juridica", 50),
    GOV("GOV", "Governamental", 250);

    private String sigla;

    private String descricao;

    private Integer quantidadeSaque;
}
