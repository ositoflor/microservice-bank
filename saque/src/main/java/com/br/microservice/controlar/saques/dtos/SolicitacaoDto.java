package com.br.microservice.controlar.saques.dtos;

import com.br.microservice.controlar.saques.domain.Conta;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SolicitacaoDto {
    private Conta conta;
    private Double valorSolicitado;
    private String idSolicitacao;
}
