package com.br.banco.usuario.dtos;

import com.br.banco.usuario.domain.enums.TipoConta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaDto {
    private String id_cliente;
    private TipoConta tipoConta;
    private Integer agencia;
}
