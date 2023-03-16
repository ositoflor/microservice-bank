package com.br.banco.usuario.dtos;

import com.br.banco.usuario.domain.enums.TipoTransacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferenciaEnviadaDto {
    private String id;
    private LocalDateTime dataHoraTransacao;
    private double valor;
    private TipoTransacao tipoTransacao;

}
