package com.br.microservice.controlar.saques.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum StatusSolicitacao {

    PD("PD", "Pendente"),
    NA("NA", "Não autorizado"),
    AT("AT", "Autorizado");

    private String sigla;
    private String descricao;
}
