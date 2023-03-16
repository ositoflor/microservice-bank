package com.br.banco.usuario.exceptionHandler;

public class BusinessException extends RuntimeException{
    public BusinessException(String msg) {
        super(msg);
    }
}
