package com.br.microservice.controlar.saques.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TipoConta {
    PF("PF", "Pessoa Fisica", 5, 10.0),
    PJ("PJ", "Pessoa Juridica", 50, 10.0),
    GOV("GOV", "Governamental", 250, 10.0);
    private String sigla;
    private String descricao;
    private Integer quantidadeSaque;
    private Double valorSaque;
}
