package com.br.banco.usuario.exceptionHandler.ClienteExceptions;

public class ClienteNotFound extends RuntimeException{
    public ClienteNotFound(String msg){
        super(msg);
    }
}
