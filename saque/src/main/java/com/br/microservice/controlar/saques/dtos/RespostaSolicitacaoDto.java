package com.br.microservice.controlar.saques.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RespostaSolicitacaoDto {

    private String idSolicitacao;
    private StatusSolicitacao statusSolicitacao;
    private Double valorDebitado;
}
