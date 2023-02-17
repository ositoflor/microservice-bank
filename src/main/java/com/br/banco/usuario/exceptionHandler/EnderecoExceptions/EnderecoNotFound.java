package com.br.banco.usuario.exceptionHandler.EnderecoExceptions;

public class EnderecoNotFound extends RuntimeException{
    public EnderecoNotFound(String msg){
        super(msg);
    }
}
