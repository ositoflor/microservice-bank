package com.br.banco.usuario.exceptionHandler;

import com.br.banco.usuario.exceptionHandler.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ResponseBody
    @ExceptionHandler(DocumentoInvalidException.class)
    private ResponseEntity<DefaultMessageException> DocuementoInvalido(DocumentoInvalidException e){
        DefaultMessageException error = new DefaultMessageException();
        error.setMensagem(e.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    private ResponseEntity<DefaultMessageException> defaultNotFound(BusinessException e){
        DefaultMessageException error = new DefaultMessageException();
        error.setMensagem(e.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<DefaultMessageException> defaultNotFound(IllegalArgumentException e){
        DefaultMessageException error = new DefaultMessageException();
        error.setMensagem("Filtro inválido, usar um dos parâmetros:  BO, DE, DOC, PIX, SA, TC, TD, TED ");
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
