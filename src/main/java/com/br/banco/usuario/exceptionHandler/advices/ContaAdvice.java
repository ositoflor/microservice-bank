package com.br.banco.usuario.exceptionHandler.advices;

import com.br.banco.usuario.exceptionHandler.ContaExceptions.ContaNotFound;
import com.br.banco.usuario.exceptionHandler.DefaultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@ControllerAdvice
public class ContaAdvice {

    @ResponseBody
    @ExceptionHandler(ContaNotFound.class)
    private ResponseEntity<DefaultException> contaInvalida(ContaNotFound e){
        DefaultException error = new DefaultException();
        error.setMensagem("Conta não encontrada.");
        error.setStatus(HttpStatus.NO_CONTENT.value());
        error.setDataHora(new Date().toString());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
