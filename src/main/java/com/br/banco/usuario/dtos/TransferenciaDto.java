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
public class TransferenciaDto {
    private String idCLienteOrigem;
    private Integer agenciaDestino;
    private Integer contaDestino;
    private Integer digitoDestino;
    private Double valor;
    private LocalDateTime data = LocalDateTime.now();
}
