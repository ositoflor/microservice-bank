package com.br.banco.usuario.dtos;

import com.br.banco.usuario.domain.Conta;
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
    private LocalDateTime dateTime;
}
