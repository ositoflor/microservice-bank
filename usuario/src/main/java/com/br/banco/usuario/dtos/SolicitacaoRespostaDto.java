package com.br.banco.usuario.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SolicitacaoRespostaDto {
    private String idSolictacao;
    private LocalDateTime dateTime;
}
