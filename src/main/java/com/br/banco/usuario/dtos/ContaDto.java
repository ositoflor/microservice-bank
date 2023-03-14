package com.br.banco.usuario.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContaDto {
    @NotNull
    private String idCliente;
    @NotBlank
    private Integer agencia;
}
