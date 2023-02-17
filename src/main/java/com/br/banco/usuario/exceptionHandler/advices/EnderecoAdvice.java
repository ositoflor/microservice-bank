package com.br.banco.usuario.exceptionHandler.advices;

import com.br.banco.usuario.exceptionHandler.DefaultException;
import com.br.banco.usuario.exceptionHandler.EnderecoExceptions.EnderecoNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@ControllerAdvice
public class EnderecoAdvice {

    @ResponseBody
    @ExceptionHandler(EnderecoNotFound.class)
    private ResponseEntity<DefaultException> enderecoInvalido(EnderecoNotFound e){
        DefaultException error = new DefaultException();
        error.setMensagem("Endereço não encontrado.");
        error.setStatus(HttpStatus.NO_CONTENT.value());
        error.setDataHora(new Date().toString());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
