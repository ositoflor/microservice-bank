package com.br.banco.usuario.exceptionHandler.ContaExceptions;

public class ContaNotFound extends RuntimeException{
    public ContaNotFound(String msg){
        super(msg);
    }
}
