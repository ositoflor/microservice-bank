package com.br.banco.usuario.exceptionHandler.advices;

import com.br.banco.usuario.exceptionHandler.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class DefaultAdvice {

    @ResponseBody
    @ExceptionHandler(DocumentoInvalidException.class)
    private ResponseEntity<DefaultMessageException> DocuementoInvalido(DocumentoInvalidException e){
        DefaultMessageException error = new DefaultMessageException();
        error.setMensagem(e.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(DefaultNotFound.class)
    private ResponseEntity<DefaultMessageException> defaultNotFound(DefaultNotFound e){
        DefaultMessageException error = new DefaultMessageException();
        error.setMensagem(e.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
