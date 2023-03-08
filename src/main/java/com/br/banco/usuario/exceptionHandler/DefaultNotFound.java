package com.br.banco.usuario.exceptionHandler;

public class DefaultNotFound extends RuntimeException{
    public DefaultNotFound(String msg) {
        super(msg);
    }
}
