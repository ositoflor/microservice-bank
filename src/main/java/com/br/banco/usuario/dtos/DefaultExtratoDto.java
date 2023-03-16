package com.br.banco.usuario.dtos;

import com.br.banco.usuario.domain.enums.TipoTransacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DefaultExtratoDto {
    private String id;
    @Column
    private Double valor;
    private TipoTransacao tipoTransacao;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataHoraTransacao;
}
