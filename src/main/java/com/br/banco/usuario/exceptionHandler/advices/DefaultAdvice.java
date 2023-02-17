package com.br.banco.usuario.exceptionHandler.advices;

import com.br.banco.usuario.exceptionHandler.CNPJInvalidException;
import com.br.banco.usuario.exceptionHandler.CPFInvalidException;
import com.br.banco.usuario.exceptionHandler.DefaultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@ControllerAdvice
public class DefaultAdvice {
    @ResponseBody
    @ExceptionHandler(CPFInvalidException.class)
    private ResponseEntity<DefaultException> CPFInvalido(CPFInvalidException e){
        DefaultException error = new DefaultException();
        error.setMensagem("CPF inválido.");
        error.setStatus(HttpStatus.NO_CONTENT.value());
        error.setDataHora(new Date().toString());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(CNPJInvalidException.class)
    private ResponseEntity<DefaultException> CNPJInvalido(CNPJInvalidException e){
        DefaultException error = new DefaultException();
        error.setMensagem("CNPJ inválido.");
        error.setStatus(HttpStatus.NO_CONTENT.value());
        error.setDataHora(new Date().toString());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
