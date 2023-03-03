package com.br.banco.usuario.exceptionHandler.advices;

import com.br.banco.usuario.exceptionHandler.CNPJInvalidException;
import com.br.banco.usuario.exceptionHandler.CPFInvalidException;
import com.br.banco.usuario.exceptionHandler.DefaultMessageException;
import com.br.banco.usuario.exceptionHandler.DocumentoInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class DefaultAdvice {
    @ResponseBody
    @ExceptionHandler(CPFInvalidException.class)
    private ResponseEntity<DefaultMessageException> CPFInvalido(CPFInvalidException e){
        DefaultMessageException error = new DefaultMessageException();
        error.setMensagem("CPF inválido.");
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(CNPJInvalidException.class)
    private ResponseEntity<DefaultMessageException> CNPJInvalido(CNPJInvalidException e){
        DefaultMessageException error = new DefaultMessageException();
        error.setMensagem("CNPJ inválido.");
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(DocumentoInvalidException.class)
    private ResponseEntity<DefaultMessageException> DocuementoInvalido(DocumentoInvalidException e){
        DefaultMessageException error = new DefaultMessageException();
        error.setMensagem("Docuemento inválido.");
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
