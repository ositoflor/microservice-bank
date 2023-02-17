package com.br.banco.usuario.exceptionHandler.advices;

import com.br.banco.usuario.exceptionHandler.ClienteExceptions.ClienteNotFound;
import com.br.banco.usuario.exceptionHandler.DefaultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@ControllerAdvice
public class ClienteAdvice {

    @ResponseBody
    @ExceptionHandler(ClienteNotFound.class)
    private ResponseEntity<DefaultException> clienteInvalido(ClienteNotFound e){
        DefaultException error = new DefaultException();
        error.setMensagem("Cliente não encontrado.");
        error.setStatus(HttpStatus.NO_CONTENT.value());
        error.setDataHora(new Date().toString());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
