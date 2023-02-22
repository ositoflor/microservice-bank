package com.br.banco.usuario.services.utils;

import br.com.caelum.stella.format.CNPJFormatter;
import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import com.br.banco.usuario.domain.Cliente;
import com.br.banco.usuario.domain.enums.TipoConta;
import com.br.banco.usuario.domain.enums.TipoDocumento;
import com.br.banco.usuario.exceptionHandler.CNPJInvalidException;
import com.br.banco.usuario.exceptionHandler.CPFInvalidException;
import com.br.banco.usuario.exceptionHandler.DocumentoInvalidException;

import java.util.Formatter;

public class ValidadorDocumento {

    public static String validar(Cliente cliente) {
        if (cliente.getTipoDocumento() == TipoDocumento.CPF) {
            CPFFormatter formatter = new CPFFormatter();
            String CPF = formatter.unformat(cliente.getDocumento());
            validarCPF(CPF);
            return CPF;
        }
        if (cliente.getTipoDocumento() == TipoDocumento.CNPJ) {
            CNPJFormatter formatter = new CNPJFormatter();
            String CNPJ = formatter.unformat(cliente.getDocumento());
            validarCNPJ(CNPJ);
            return CNPJ;
        }
        throw new DocumentoInvalidException();
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

    public static TipoConta validarTipoConta(Cliente cliente){
        if (cliente.getTipoDocumento() == TipoDocumento.CPF) {
            validarCPF(cliente.getDocumento());
            return TipoConta.PF;
        }

        if (cliente.getTipoDocumento() == TipoDocumento.CNPJ) {
            validarCNPJ(cliente.getDocumento());
            return TipoConta.PJ;
        }
        throw new DocumentoInvalidException();
    }
}
