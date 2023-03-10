package com.br.banco.usuario.exceptionHandler.advices;

import com.br.banco.usuario.exceptionHandler.ContaExceptions.SaldoNotValid;
import com.br.banco.usuario.exceptionHandler.DefaultMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ContaAdvice {
    @ResponseBody
    @ExceptionHandler(SaldoNotValid.class)
    private ResponseEntity<DefaultMessageException> valorSaqueInsuficiente(SaldoNotValid e){
        DefaultMessageException error = new DefaultMessageException();
        error.setMensagem("Saldo insuficiente.");
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
