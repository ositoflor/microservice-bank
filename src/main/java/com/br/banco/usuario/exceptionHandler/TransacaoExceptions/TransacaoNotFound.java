package com.br.banco.usuario.exceptionHandler.TransacaoExceptions;


public class TransacaoNotFound extends RuntimeException{
   public TransacaoNotFound(String msg) {
       super(msg);
   }
}
