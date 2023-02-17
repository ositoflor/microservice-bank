package com.br.banco.usuario.exceptionHandler;

public class CNPJInvalidException extends RuntimeException{
    public CNPJInvalidException(String msg) {
        super(msg);
    }
}
