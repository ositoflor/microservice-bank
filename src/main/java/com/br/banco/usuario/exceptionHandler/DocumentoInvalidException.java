package com.br.banco.usuario.exceptionHandler;

public class DocumentoInvalidException extends RuntimeException{
    public DocumentoInvalidException(String msg) {
        super(msg);
    }
}
