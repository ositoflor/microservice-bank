package com.br.banco.usuario.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TipoDocumento {
    CPF("CPF", "Cadastro de Pessoa Física"),
    CNPJ("CNJP", "Cadastro Nacional de Pessoas Jurídicas");
    private String sigla;
    private String descricao;
}
