package com.br.banco.usuario.dtos;

import com.br.banco.usuario.domain.enums.TipoConta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaDto {
    private String idCliente;
    private String documentoCliente;
    private Integer agencia;
}
