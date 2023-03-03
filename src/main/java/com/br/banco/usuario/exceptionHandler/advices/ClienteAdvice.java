package com.br.banco.usuario.exceptionHandler.advices;

import com.br.banco.usuario.exceptionHandler.ClienteExceptions.ClienteNotFound;
import com.br.banco.usuario.exceptionHandler.DefaultMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ClienteAdvice {

    @ResponseBody
    @ExceptionHandler(ClienteNotFound.class)
    private ResponseEntity<DefaultMessageException> clienteInvalido(ClienteNotFound e){
        DefaultMessageException error = new DefaultMessageException();
        error.setMensagem("Cliente não encontrado.");
        error.setStatus(HttpStatus.NO_CONTENT.value());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
