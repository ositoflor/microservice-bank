package com.br.banco.usuario.exceptionHandler.advices;

import com.br.banco.usuario.exceptionHandler.ContaExceptions.ContaNotFound;
import com.br.banco.usuario.exceptionHandler.DefaultException;
import com.br.banco.usuario.exceptionHandler.TransacaoExceptions.TransacaoNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@ControllerAdvice
public class TransacaoAdvice {
    @ResponseBody
    @ExceptionHandler(TransacaoNotFound.class)
    private ResponseEntity<DefaultException> transacaoInvalida(TransacaoNotFound e){
        DefaultException error = new DefaultException();
        error.setMensagem("Transação não encontrada.");
        error.setStatus(HttpStatus.NO_CONTENT.value());
        error.setDataHora(new Date().toString());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
