package com.br.banco.usuario.exceptionHandler;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DefaultMessageException {

    private int status;
    private String mensagem;
    private LocalDateTime dataHora = LocalDateTime.now();
}
