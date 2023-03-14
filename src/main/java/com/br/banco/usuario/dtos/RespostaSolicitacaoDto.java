package com.br.banco.usuario.dtos;

import com.br.banco.usuario.domain.enums.StatusSolicitacao;
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