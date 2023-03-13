package com.br.banco.usuario.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaqueDto {
    private Integer agencia;
    private Integer conta;
    private Integer digito;
    private Double valorSaque;
    private LocalDateTime data = LocalDateTime.now();
}
