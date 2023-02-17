package com.br.banco.usuario.exceptionHandler;

public class CPFInvalidException extends RuntimeException{
    public CPFInvalidException(String msg){
        super(msg);
    }
}
