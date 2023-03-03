package com.br.banco.usuario.exceptionHandler.advices;

import com.br.banco.usuario.exceptionHandler.DefaultMessageException;
import com.br.banco.usuario.exceptionHandler.EnderecoExceptions.EnderecoNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class EnderecoAdvice {

    @ResponseBody
    @ExceptionHandler(EnderecoNotFound.class)
    private ResponseEntity<DefaultMessageException> enderecoInvalido(EnderecoNotFound e){
        DefaultMessageException error = new DefaultMessageException();
        error.setMensagem("Endereço não encontrado.");
        error.setStatus(HttpStatus.NO_CONTENT.value());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
