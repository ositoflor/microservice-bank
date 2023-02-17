package com.br.banco.usuario.services.utils;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import com.br.banco.usuario.exceptionHandler.CNPJInvalidException;
import com.br.banco.usuario.exceptionHandler.CPFInvalidException;

public class ValidadorDocumento {

    public static Boolean validar(String doc) {
        if (doc.length() == 11) {
            return validarCPF(doc);
        }
        return validarCNPJ(doc);
    }


    public static Boolean validarCPF(String doc) {
        CPFValidator validator = new CPFValidator();

        try{
            validator.assertValid(doc);
            return true;
        }catch (InvalidStateException e) {
            throw new CPFInvalidException(e.getMessage());
        }
    }

    public static Boolean validarCNPJ(String doc) {
        CNPJValidator validator = new CNPJValidator();

        try{
            validator.assertValid(doc);
            return true;
        }catch (InvalidStateException e) {
            throw new CNPJInvalidException(e.getMessage());
        }
    }
}
