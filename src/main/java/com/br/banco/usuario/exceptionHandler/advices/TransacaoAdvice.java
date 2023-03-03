package com.br.banco.usuario.exceptionHandler.advices;

import com.br.banco.usuario.exceptionHandler.DefaultMessageException;
import com.br.banco.usuario.exceptionHandler.TransacaoExceptions.TransacaoNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class TransacaoAdvice {
    @ResponseBody
    @ExceptionHandler(TransacaoNotFound.class)
    private ResponseEntity<DefaultMessageException> transacaoInvalida(TransacaoNotFound e){
        DefaultMessageException error = new DefaultMessageException();
        error.setMensagem("Transação não encontrada.");
        error.setStatus(HttpStatus.NO_CONTENT.value());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
